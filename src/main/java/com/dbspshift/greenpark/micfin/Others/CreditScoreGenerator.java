package com.dbspshift.greenpark.micfin.Others;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import com.dbspshift.greenpark.micfin.repository.LoanInfoRepository;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurRepository;
import com.dbspshift.greenpark.micfin.repository.RepaymentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Component
public class CreditScoreGenerator {

    /*'LIMIT_BAL', 'SEX', 'EDUCATION', 'MARRIAGE', 'AGE', 'RS_1', 'RS_2',
        'RS_3', 'RS_4', 'RS_5', 'RS_6', 'PAY_AMT1', 'PAY_AMT2', 'PAY_AMT3',
        'PAY_AMT4', 'PAY_AMT5', 'PAY_AMT6'*/

    private static String ML_LAMBDA_URI = "https://5xsvu5qi2e.execute-api.ap-southeast-1.amazonaws.com/default/micfin-lambda";
    private HashMap<Integer,String> inputParamHashMap = new HashMap<>();

    @Autowired
    MicroEntrepreneurRepository microEntrepreneurRepository;
    @Autowired
    LoanInfoRepository loanInfoRepository;

    public HttpURLConnection getLambdaConnection() throws Exception {
        URL url = new URL (ML_LAMBDA_URI);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        return con;
    }

    public void getCreditScore(RepaymentInfo repaymentInfo) throws Exception {
        HttpURLConnection con = getLambdaConnection();
        //String jsonInputString = "{\"values\": [1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8]}";
        String jsonInputString = getInputParametersInJsonFormat(repaymentInfo);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
        con.disconnect();
    }

    public String getInputParametersInJsonFormat(RepaymentInfo repaymentInfo){
        String loanId = repaymentInfo.getLoanId();
        LoanInfo loanInfo = loanInfoRepository.findByLoanId(loanId);

        List<RepaymentInfo> repaymentInfoList = loanInfo.getRepaymentInfoList();
        int size = repaymentInfoList.size();
        List<RepaymentInfo> repaymentInfosSixMonths = repaymentInfoList;
        if(size >5){
            repaymentInfosSixMonths = repaymentInfoList.subList(size - 6, size - 1);
        }

        String parameters = "[";
        int i=0;
        for(i=0; i<repaymentInfosSixMonths.size();i++){
            RepaymentInfo repaymentInfo1 = repaymentInfosSixMonths.get(i);
            inputParamHashMap.put(6+i, String.valueOf(repaymentInfo1.getPaymentDelayedInMonths()));
            inputParamHashMap.put(12+i, String.valueOf(repaymentInfo1.getPayment()));
        }
        if(i<5){
            i=i+1;
            while(i!=5){
                inputParamHashMap.put(6+i, "0");
                inputParamHashMap.put(12+i, "0");
            }
        }

        MicroEntrepreneur byMicroEntrepreneurId = microEntrepreneurRepository.findByMicroEntrepreneurId(repaymentInfo.getMicroEntrepreneurId());
        String gender = byMicroEntrepreneurId.getGender();
        String highestEducation = byMicroEntrepreneurId.getHighestEducation();
        String maritialStatus = byMicroEntrepreneurId.getMaritialStatus();
        byMicroEntrepreneurId.getDob();
        inputParamHashMap.put(1, String.valueOf(repaymentInfo.getPayment()));
        inputParamHashMap.put(2, gender.equals("female")?"1":"0");
        inputParamHashMap.put(3, highestEducation.contains("graduate")?"8":"3");
        inputParamHashMap.put(4, maritialStatus.equals("married")?"1":"0");
        inputParamHashMap.put(5, "37");

        String jsonInput = "[";
        for(int j=1;j<18;j++) {
            String s = inputParamHashMap.get(j);
            jsonInput = jsonInput + s;
            if(j!=17){
                jsonInput = jsonInput + ",";
            }
        }
        jsonInput = jsonInput + "]";
        return jsonInput;
    }
}


//Predicate<RepaymentInfo> predFilterByMeId = rp -> rp.getMicroEntrepreneurId().equals(microEntrepreneurId);
//List<RepaymentInfo> collect = repaymentInfoRepository.findAll().stream().filter(p -> predFilterByMeId.test(p)).collect(Collectors.toList());
//List<RepaymentInfo> repaymentInfosSixMonths;
//if(collect.size()>6){
//repaymentInfosSixMonths = collect.subList(0, 5);
//}


  /*private Json requestCreditScore(){

    }*/

// HTTP POST request
    /*public void sendPost() throws Exception {

        String url = "https://5xsvu5qi2e.execute-api.ap-southeast-1.amazonaws.com/default/micfin-lambda";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }*/

/*    public Mono<String> sendPostWebClient(){
        WebClient webClient = WebClient.create();

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.set("values","1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8");
        Mono<String> result = webClient.post()
                .uri( "https://5xsvu5qi2e.execute-api.ap-southeast-1.amazonaws.com/default/micfin-lambda" )
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept( MediaType.APPLICATION_JSON )
                .body( BodyInserters.fromFormData(formData))
                .retrieve().bodyToMono(String.class);

        System.out.println(result);
        return result;
    }*/

