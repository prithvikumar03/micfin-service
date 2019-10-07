package com.dbspshift.greenpark.micfin.Others;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
//import org.apache.commons.lang.time.DateUtils;
import com.dbspshift.greenpark.micfin.reactiverepo.LoanInfoReactiveRepo;
import com.dbspshift.greenpark.micfin.reactiverepo.MicroEntrepreneurReactiveRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.*;

@Component
public class CreditScoreGenerator {
/*    'LIMIT_BAL', 'SEX', 'EDUCATION', 'MARRIAGE', 'AGE', 'RS_1', 'RS_2',
        'RS_3', 'RS_4', 'RS_5', 'RS_6', 'PAY_AMT1', 'PAY_AMT2', 'PAY_AMT3',
        'PAY_AMT4', 'PAY_AMT5', 'PAY_AMT6'*/

    private static String ML_LAMBDA_URI = "https://5xsvu5qi2e.execute-api.ap-southeast-1.amazonaws.com/default/micfin-lambda";
    //private HashMap<Integer,String> inputParamHashMap = new HashMap<>();

    private static DecimalFormat df2 = new DecimalFormat("#.0#"); //so that 3.0 remains as 3.0 and not 3 AND 0.5 as 0.5 and not .5

    @Autowired
    MicroEntrepreneurReactiveRepo microEntrepreneurRepository;
    @Autowired
    LoanInfoReactiveRepo loanInfoRepository;

    public MicroEntrepreneur getCreditScore(RepaymentInfo repaymentInfo) throws Exception {
        String walletBalanceUrl = ML_LAMBDA_URI;

        Optional<MicroEntrepreneur> byMicroEntrepreneurId = microEntrepreneurRepository.findByMicroEntrepreneurId(repaymentInfo.getMicroEntrepreneurId()).blockOptional();
        String loanId = repaymentInfo.getLoanId();
        Optional<LoanInfo> loanInfo = loanInfoRepository.findByLoanId(loanId).blockOptional();

        try {
            String creditScore = byMicroEntrepreneurId.get().getCreditScore();
            if(creditScore==null){
                creditScore="5.0";
                byMicroEntrepreneurId.get().setCreditScore(creditScore);
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            String jsonInputString = getInputParametersInJsonFormat(repaymentInfo, loanInfo.get(), byMicroEntrepreneurId.get());

            HttpEntity<String> httpEntity = new HttpEntity<String>(jsonInputString, httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(walletBalanceUrl, httpEntity, String.class);

            JSONObject jsonObj = new JSONObject(response);
            String balance = jsonObj.get("body").toString();

            Double creditIncrDecr = jasonToCreditScoreIncrDecr(balance);
            byMicroEntrepreneurId.get().setCreditScore(calcCreditScore(creditScore,creditIncrDecr));

        }
        catch(Exception e){

        }
        return byMicroEntrepreneurId.get();
    }

    public String calcCreditScore(String oldCreditScore,Double incrDecrValue){
        double newCreditScore = Double.parseDouble(oldCreditScore);
        if (!(newCreditScore + incrDecrValue > 10) && !(newCreditScore + incrDecrValue < 0)) {
            newCreditScore = newCreditScore + incrDecrValue;
            String stringCreditScore = df2.format(newCreditScore);

        }
        return String.valueOf(newCreditScore);
    }

    public Double jasonToCreditScoreIncrDecr(String jsonRetValue){
        double creditIncrDecr = 0.0;
        Double probablity = 0.0;
        try {
            if (jsonRetValue.contains("#")) {

                String[] split = jsonRetValue.replaceAll("\"","").split("#");
                if (split.length > 1) {
                    String s = split[1];
                    probablity = Double.parseDouble(s);
                }
                int i = (int) (probablity/20);
                switch (i){
                    case 1:
                        creditIncrDecr = 0.4;
                        break;
                    case 2:
                        creditIncrDecr = 0.2;
                        break;
                    case 3:
                        creditIncrDecr = -0.2;
                        break;
                    case 4:
                        creditIncrDecr = -1;
                        break;

                }
            }
        }catch(Exception e){

        }
        return creditIncrDecr;
    }

    public String getInputParametersInJsonFormat(RepaymentInfo repaymentInfo,LoanInfo loanInfo,MicroEntrepreneur byMicroEntrepreneurId){
        //String loanId = repaymentInfo.getLoanId();
        //LoanInfo loanInfo = loanInfoRepository.findByLoanId(loanId);
        String jsonInput = "{\"values\": [";

        try {
            HashMap<Integer, String> inputParamHashMap = new HashMap<>();
            List<RepaymentInfo> repaymentInfoList = loanInfo.getRepaymentInfoList();
            int size = repaymentInfoList.size();
            List<RepaymentInfo> repaymentInfosSixMonths = repaymentInfoList;
            if (size > 5) {
                repaymentInfosSixMonths = repaymentInfoList.subList(size - 6, size);
            }

            //String parameters = "{\"values\": [";
            int i = 0;
            for (i = 0; i < repaymentInfosSixMonths.size(); i++) {
                RepaymentInfo repaymentInfo1 = repaymentInfosSixMonths.get(i);
                inputParamHashMap.put(6 + i, String.valueOf(repaymentInfo1.getPaymentDelayedInMonths()));
                inputParamHashMap.put(12 + i, String.valueOf(repaymentInfo1.getPayment()));
            }
            if (i < 5) {
                //i=i+1;
                while (i < 6) {
                    inputParamHashMap.put(6 + i, "-1");
                    inputParamHashMap.put(12 + i, "-1");
                    i++;
                }
            }

            //MicroEntrepreneur byMicroEntrepreneurId = microEntrepreneurRepository.findByMicroEntrepreneurId(repaymentInfo.getMicroEntrepreneurId());
            String gender = byMicroEntrepreneurId.getGender();
            String highestEducation = byMicroEntrepreneurId.getHighestEducation();
            String maritialStatus = byMicroEntrepreneurId.getMaritialStatus();

            inputParamHashMap.put(1, String.valueOf(repaymentInfo.getPayment()));
            inputParamHashMap.put(2, gender.contains("female") ? "2" : "1");

            String education = highestEducation.contains("graduate") ? "1" : highestEducation.contains("university") ? "2" : "3";
            inputParamHashMap.put(3, education);
            inputParamHashMap.put(4, maritialStatus.equals("married") ? "1" : "2");

            Date dob = byMicroEntrepreneurId.getDob();
            Date now = new Date();
            long diffSec = (now.getTime() - dob.getTime())/1000;
            int age = Math.toIntExact(diffSec / (31556952));
            inputParamHashMap.put(5, String.valueOf(age));

            //String jsonInput = "[";
            for (int j = 1; j < 18; j++) {
                String s = inputParamHashMap.get(j);
                jsonInput = jsonInput + s;
                if (j != 17) {
                    jsonInput = jsonInput + ",";
                }
            }
            jsonInput = jsonInput + "]}";
        }catch(Exception e){

        }
        return jsonInput;
    }
}