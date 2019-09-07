package com.dbspshift.greenpark.micfin.Others;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import com.dbspshift.greenpark.micfin.repository.LoanInfoRepository;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class CreditScoreGeneratorTest {

    @Configuration
    static class TestConfiguration {
        @Bean
        public CreditScoreGenerator getCreditScoreGenerator() {
            return new CreditScoreGenerator();
        }
    }

    RepaymentInfo repaymentInfo;
    LoanInfo loanInfo = new LoanInfo();
    MicroEntrepreneur microEntrepreneur = new MicroEntrepreneur();

    @Autowired
    CreditScoreGenerator creditScoreGenerator;

    @MockBean
    LoanInfoRepository loanInfoRepository;
    @MockBean
    MicroEntrepreneurRepository microEntrepreneurRepository;

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
        //creditScoreGenerator = new CreditScoreGenerator();
        repaymentInfo = new RepaymentInfo();
        repaymentInfo.setLoanId("123");
        repaymentInfo.setLoanAmount(1000);
        repaymentInfo.setMicroEntrepreneurId("456");
        repaymentInfo.setPayment(100);

        //List<RepaymentInfo> repaymentInfoList = new ArrayList<>();
        RepaymentInfo repaymentInfo1 = new RepaymentInfo();
        repaymentInfo1.setPayment(1000);
        repaymentInfo1.setPaymentDelayedInMonths(1);
        loanInfo.addToRepaymentInfoList(repaymentInfo1);
        RepaymentInfo repaymentInfo2 = new RepaymentInfo();
        repaymentInfo2.setPayment(1100);
        repaymentInfo2.setPaymentDelayedInMonths(0);
        loanInfo.addToRepaymentInfoList(repaymentInfo2);
        RepaymentInfo repaymentInfo3 = new RepaymentInfo();
        repaymentInfo3.setPayment(1200);
        repaymentInfo3.setPaymentDelayedInMonths(2);
        loanInfo.addToRepaymentInfoList(repaymentInfo3);

        microEntrepreneur.setMaritialStatus("married");
        microEntrepreneur.setGender("female");
        microEntrepreneur.setHighestEducation("graduate");
        microEntrepreneur.setCreditScore("5.0");

        Mockito.when(loanInfoRepository.findByLoanId("123")).thenReturn(Optional.ofNullable(loanInfo));
        Mockito.when(microEntrepreneurRepository.findByMicroEntrepreneurId("456")).thenReturn(Optional.ofNullable(microEntrepreneur));
    }

    @Test
    public void getLambdaConnection() {
    }

    @Test
    public void getCreditScore() throws Exception {
        MicroEntrepreneur creditScore = creditScoreGenerator.getCreditScore(repaymentInfo);
        System.out.println(creditScore);
    }

    @Test
    public void getInputParametersInJsonFormat() {
        creditScoreGenerator.getInputParametersInJsonFormat(repaymentInfo,loanInfo,microEntrepreneur);
    }

    @Test
    public void sendPost() throws Exception {

    }



}