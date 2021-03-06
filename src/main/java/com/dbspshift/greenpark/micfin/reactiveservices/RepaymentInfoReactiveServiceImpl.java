package com.dbspshift.greenpark.micfin.reactiveservices;

import com.dbspshift.greenpark.micfin.Others.CreditScoreGenerator;
import com.dbspshift.greenpark.micfin.Others.LoanCalculationsManager;
import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import com.dbspshift.greenpark.micfin.exceptions.RepaymentInfoNotFound;
/*import com.dbspshift.greenpark.micfin.repository.LoanInfoRepository;
import com.dbspshift.greenpark.micfin.repository.MFIRepository;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurRepository;
import com.dbspshift.greenpark.micfin.services.RepaymentInfoService;*/
import com.dbspshift.greenpark.micfin.reactiverepo.LoanInfoReactiveRepo;
import com.dbspshift.greenpark.micfin.reactiverepo.MFIReactiveRepo;
import com.dbspshift.greenpark.micfin.reactiverepo.MicroEntrepreneurReactiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class RepaymentInfoReactiveServiceImpl implements RepaymentInfoReactiveService{

    @Autowired
    LoanInfoReactiveRepo loanInfoRepository;
    @Autowired
    CreditScoreGenerator creditScoreGenerator;
    @Autowired
    LoanCalculationsManager loanCalculationsManager;
    @Autowired
    MicroEntrepreneurReactiveRepo microEntrepreneurRepository;
    @Autowired
    MFIReactiveRepo mfiRepository;

    @Override
    public Mono<RepaymentInfo> registerRepaymentInfo(RepaymentInfo repaymentInfo) throws Exception {

        Optional<LoanInfo> optionalLoanInfo = loanInfoRepository.findByLoanId(repaymentInfo.getLoanId()).blockOptional();
        Optional<MicroEntrepreneur> byMicroEntrepreneurId = microEntrepreneurRepository.findByMicroEntrepreneurId(repaymentInfo.getMicroEntrepreneurId()).blockOptional();

        MicroEntrepreneur microEntrepreneur;
        if(byMicroEntrepreneurId.isPresent()){
            microEntrepreneur = byMicroEntrepreneurId.get();
            System.out.println("OLD CREDIT SCORE" + microEntrepreneur.getCreditScore());
            boolean equals = microEntrepreneur.getMfiId().toUpperCase().trim().equals(repaymentInfo.getMfiId().toUpperCase().trim());
            if(!equals){
                throw new RepaymentInfoNotFound("MicroEntrepreneur has not registered with this MFI, LoanInfo - [ID = "+repaymentInfo.getMfiId()+"  ]");
            }
        }
        else{
            throw new RepaymentInfoNotFound("MicroEntrepreneur has no record of this loan, LoanInfo - [ID = "+repaymentInfo.getLoanId()+"  ]");
        }

        if(optionalLoanInfo.isPresent()) {
            LoanInfo loanInfo = optionalLoanInfo.get();
            repaymentInfo.setLoanAmount(loanInfo.getLoanAmount());
            loanInfo.addToRepaymentInfoList(repaymentInfo);
            updateLoanInfoLegder(repaymentInfo,loanInfo);
            loanInfoRepository.save(loanInfo).block();

            //Require to get 6 months transaction from RepaymentInfo
            //6 months delayed info from RepaymentInfo to get credit score
            MicroEntrepreneur microEntrepreneurUpdatedCreditScore = creditScoreGenerator.getCreditScore(repaymentInfo);
            if (microEntrepreneurUpdatedCreditScore != null) {
                System.out.println("NEW CREDIT SCORE" + microEntrepreneurUpdatedCreditScore.getCreditScore());
                microEntrepreneurRepository.save(microEntrepreneurUpdatedCreditScore).block();
            }
            return Mono.just(repaymentInfo);
        }
        else{
            throw new RepaymentInfoNotFound("No loan exists for this repayment, LoanInfo - [ID = "+repaymentInfo.getLoanId()+"  ]");
        }
    }

    @Override
    public Flux<RepaymentInfo> getRepaymentInfoByLoanId(String loanId) throws Exception {
        Optional<LoanInfo> loanInfo = loanInfoRepository.findByLoanId(loanId).blockOptional();
        if(loanInfo.isPresent()){
            return Flux.fromIterable(loanInfo.get().getRepaymentInfoList());
        }
        else{
            throw new RepaymentInfoNotFound("No loan exists for this locan, LoanInfo - [ID = "+loanId+"  ]");
        }
        //return null;
    }

    @Override
    public Flux<RepaymentInfo> getAllRepaymentInfoByMicroEntrepreneurId(String microEntrepreneurId) throws Exception {
        Optional<LoanInfo> byMicroEntrepreneurId = loanInfoRepository.findByMicroEntrepreneurId(microEntrepreneurId).blockOptional();
        if(byMicroEntrepreneurId.isPresent()) {
            LoanInfo loanInfo = byMicroEntrepreneurId.get();
            return Flux.fromIterable(loanInfo.getRepaymentInfoList());
        }
        else{
            throw new RepaymentInfoNotFound("No loan exists for this microentrepreneur, LoanInfo - [ID = "+microEntrepreneurId+"  ]");
        }
        //return null;
    }

    public void updateLoanInfoLegder(RepaymentInfo repaymentInfo,LoanInfo loanInfo){
        Integer rePayment = repaymentInfo.getPayment();
        Double loanBalance = loanInfo.getLoanBalance();
        Double totalInterestPaid = loanInfo.getTotalInterestPaid();
        Double totalPrincipalPaid = loanInfo.getTotalPrincipalPaid();
        Double emi = loanInfo.getEmi();
        Integer interestRate = loanInfo.getInterestRate();

        double interestAmt = loanCalculationsManager.getInterest(loanBalance, interestRate);
        //loanInfo.setTotalInterestPaid(totalInterestPaid + interestAmt);
        if(rePayment>interestAmt){
            double amtPaidTowPrincipal = rePayment - interestAmt;
            double totalPrincipal = totalPrincipalPaid + amtPaidTowPrincipal;
            loanInfo.setTotalPrincipalPaid(totalPrincipal);
            loanInfo.setLoanBalance(loanBalance-amtPaidTowPrincipal);
            loanInfo.setTotalInterestPaid(totalInterestPaid + interestAmt);
        }
        else{
            loanInfo.setTotalInterestPaid(totalInterestPaid + rePayment);
        }
        loanInfoRepository.save(loanInfo);
    }

}

//Predicate<RepaymentInfo> predFilterByMeId = rp -> rp.getMicroEntrepreneurId().equals(id);
//List<RepaymentInfo> collect = repository.findAll().stream().filter(p -> predFilterByMeId.test(p)).collect(Collectors.toList());

            /*Integer rePayment = repaymentInfo.getPayment();
            Double loanBalance = loanInfo.getLoanBalance();
            Double totalInterestPaid = loanInfo.getTotalInterestPaid();
            Double totalPrincipalPaid = loanInfo.getTotalPrincipalPaid();
            Double emi = loanInfo.getEmi();
            Integer interestRate = loanInfo.getInterestRate();

            double interestAmt = loanCalculationsManager.getInterest(loanBalance, interestRate);
            //loanInfo.setTotalInterestPaid(totalInterestPaid + interestAmt);
            if(rePayment>interestAmt){
                double v = totalPrincipalPaid + (rePayment - interestAmt);
                loanInfo.setTotalPrincipalPaid(v);
                loanInfo.setLoanBalance(loanBalance-v);
                loanInfo.setTotalInterestPaid(totalInterestPaid + interestAmt);
            }
            else{
                loanInfo.setTotalInterestPaid(totalInterestPaid + rePayment);
            }
            loanInfoRepository.save(loanInfo);*/


            /*    //RepaymentInfoRepository repository;
    @Autowired
    LoanInfoRepository loanInfoRepository;
    @Autowired
    CreditScoreGenerator creditScoreGenerator;
    @Autowired
    LoanCalculationsManager loanCalculationsManager;
    @Autowired
    MicroEntrepreneurRepository microEntrepreneurRepository;
    @Autowired
    MFIRepository mfiRepository;

    @Override
    public RepaymentInfo registerRepaymentInfo(RepaymentInfo repaymentInfo) throws Exception {
        ///*'LIMIT_BAL', 'SEX', 'EDUCATION', 'MARRIAGE', 'AGE', from MicroEntrepreneur
        //loanInfoRepository.f

        Optional<LoanInfo> optionalLoanInfo = loanInfoRepository.findByLoanId(repaymentInfo.getLoanId());
        Optional<MicroEntrepreneur> byMicroEntrepreneurId = microEntrepreneurRepository.findByMicroEntrepreneurId(repaymentInfo.getMicroEntrepreneurId());

        MicroEntrepreneur microEntrepreneur;
        if(byMicroEntrepreneurId.isPresent()){
            microEntrepreneur = byMicroEntrepreneurId.get();
            boolean equals = microEntrepreneur.getMfiId().toUpperCase().trim().equals(repaymentInfo.getMfiId().toUpperCase().trim());
            if(!equals){
                throw new RepaymentInfoNotFound("MicroEntrepreneur has not registered with this MFI, LoanInfo - [ID = "+repaymentInfo.getMfiId()+"  ]");
            }
        }
        else{
            throw new RepaymentInfoNotFound("MicroEntrepreneur has no record of this loan, LoanInfo - [ID = "+repaymentInfo.getLoanId()+"  ]");
        }

        if(optionalLoanInfo.isPresent()) {
            LoanInfo loanInfo = optionalLoanInfo.get();
            repaymentInfo.setLoanAmount(loanInfo.getLoanAmount());
            loanInfo.addToRepaymentInfoList(repaymentInfo);
            updateLoanInfoLegder(repaymentInfo,loanInfo);
            loanInfoRepository.save(loanInfo);

            //Require to get 6 months transaction from RepaymentInfo
            //6 months delayed info from RepaymentInfo to get credit score
            //MicroEntrepreneur microEntrepreneur = creditScoreGenerator.getCreditScore(repaymentInfo);
            if (microEntrepreneur != null) {
                microEntrepreneurRepository.save(microEntrepreneur);
            }
            return repaymentInfo;
        }
        else{
            throw new RepaymentInfoNotFound("No loan exists for this repayment, LoanInfo - [ID = "+repaymentInfo.getLoanId()+"  ]");
        }
    }



    @Override
    public RepaymentInfo getRepaymentInfoById(String id) throws Exception {
       *//* Optional<RepaymentInfo> byId = repository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else*//*
            return null;
    }

    @Override
    public List<RepaymentInfo> getAllRepaymentInfoByMicroEntrepreneurId(String microEntrepreneurId) throws Exception {
        Optional<LoanInfo> byMicroEntrepreneurId = loanInfoRepository.findByMicroEntrepreneurId(microEntrepreneurId);
        if(byMicroEntrepreneurId.isPresent()) {
            LoanInfo loanInfo = byMicroEntrepreneurId.get();
            return loanInfo.getRepaymentInfoList();
        }
        else{
            throw new RepaymentInfoNotFound("No loan exists for this microentrepreneur, LoanInfo - [ID = "+microEntrepreneurId+"  ]");
        }
    }*/