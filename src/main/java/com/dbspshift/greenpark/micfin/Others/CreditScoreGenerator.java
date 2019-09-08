package com.dbspshift.greenpark.micfin.Others;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import com.dbspshift.greenpark.micfin.repository.LoanInfoRepository;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurRepository;
import com.dbspshift.greenpark.micfin.repository.RepaymentInfoRepository;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import springfox.documentation.spring.web.json.Json;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Component
public class CreditScoreGenerator {

    /*'LIMIT_BAL', 'SEX', 'EDUCATION', 'MARRIAGE', 'AGE', 'RS_1', 'RS_2',
        'RS_3', 'RS_4', 'RS_5', 'RS_6', 'PAY_AMT1', 'PAY_AMT2', 'PAY_AMT3',
        'PAY_AMT4', 'PAY_AMT5', 'PAY_AMT6'*/

    private static String ML_LAMBDA_URI = "https://5xsvu5qi2e.execute-api.ap-southeast-1.amazonaws.com/default/micfin-lambda";
    //private HashMap<Integer,String> inputParamHashMap = new HashMap<>();

    @Autowired
    MicroEntrepreneurRepository microEntrepreneurRepository;
    @Autowired
    LoanInfoRepository loanInfoRepository;

    public MicroEntrepreneur getCreditScore(RepaymentInfo repaymentInfo) throws Exception {
        String walletBalanceUrl = ML_LAMBDA_URI;

        Optional<MicroEntrepreneur> byMicroEntrepreneurId = microEntrepreneurRepository.findByMicroEntrepreneurId(repaymentInfo.getMicroEntrepreneurId());
        String loanId = repaymentInfo.getLoanId();
        Optional<LoanInfo> loanInfo = loanInfoRepository.findByLoanId(loanId);

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
            //int[] arry = {1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8};
            //json.put("values", arry);

            //String temp = "{\"values\": [1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8]}";
            HttpEntity<String> httpEntity = new HttpEntity<String>(jsonInputString, httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(walletBalanceUrl, httpEntity, String.class);

            JSONObject jsonObj = new JSONObject(response);
            String balance = jsonObj.get("body").toString();

            Double creditIncrDecr = jasonToCreditScore(balance);

            double newCreditScore = Double.parseDouble(creditScore);
            if (!(newCreditScore + creditIncrDecr > 10) && !(newCreditScore + creditIncrDecr < 0)) {
                newCreditScore = newCreditScore + creditIncrDecr;
                byMicroEntrepreneurId.get().setCreditScore(String.valueOf(newCreditScore));
            }
        }
        catch(Exception e){

        }
        return byMicroEntrepreneurId.get();
    }

    public Double jasonToCreditScore(String jsonRetValue){
        double creditIncrDecr = 0.0;
        Double probablity = 0.0;
        try {
            if (jsonRetValue.contains("#")) {

                String[] split = jsonRetValue.replaceAll("\"","").split("#");
                if (split.length > 1) {
                    String s = split[1];
                    probablity = Double.parseDouble(s);
                }
                int i = probablity.intValue() % 20;
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