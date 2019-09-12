package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.Others.CreditScoreGenerator;
import com.dbspshift.greenpark.micfin.Others.LoanCalculationsManager;
import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import com.dbspshift.greenpark.micfin.exceptions.LoanInfoNotFoundException;
import com.dbspshift.greenpark.micfin.exceptions.RepaymentInfoNotFound;
import com.dbspshift.greenpark.micfin.repository.LoanInfoRepository;
import com.dbspshift.greenpark.micfin.repository.MFIRepository;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RepaymentInfoServiceImpl implements RepaymentInfoService {

    //RepaymentInfoRepository repository;
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
        //List<RepaymentInfo> allRepaymentInfoByMicroEntrepreneurId = getAllRepaymentInfoByMicroEntrepreneurId(repaymentInfo.getMicroEntrepreneurId());
        ///*'LIMIT_BAL', 'SEX', 'EDUCATION', 'MARRIAGE', 'AGE', from MicroEntrepreneur
        //loanInfoRepository.f

        Optional<LoanInfo> optionalLoanInfo = loanInfoRepository.findByLoanId(repaymentInfo.getLoanId());
        //Optional<MFI> byMfiId = mfiRepository.findByMfiId(repaymentInfo.getMfiId());
        Optional<MicroEntrepreneur> byMicroEntrepreneurId = microEntrepreneurRepository.findByMicroEntrepreneurId(repaymentInfo.getMicroEntrepreneurId());

        if(byMicroEntrepreneurId.isPresent()){
            MicroEntrepreneur microEntrepreneur = byMicroEntrepreneurId.get();
            boolean equals = microEntrepreneur.getMfiId().toUpperCase().trim().equals(repaymentInfo.getMfiId().toUpperCase().trim());
            if(!equals){
                throw new RepaymentInfoNotFound("MicroEntrepreneur has not registered with this MFI, LoanInfo - [ID = "+repaymentInfo.getMfiId()+"  ]");
            }
        }
        else{
            throw new RepaymentInfoNotFound("MicroEntrepreneur has no record of this loan, LoanInfo - [ID = "+repaymentInfo.getLoanId()+"  ]");
        }

        if(optionalLoanInfo.isPresent()) {
            //List<RepaymentInfo> repaymentInfoList = loanInfo.getRepaymentInfoList();
            LoanInfo loanInfo = optionalLoanInfo.get();
            repaymentInfo.setLoanAmount(loanInfo.getLoanAmount());
            loanInfo.addToRepaymentInfoList(repaymentInfo);
            //RepaymentInfo save = repository.save(repaymentInfo);
            updateLoanInfoLegder(repaymentInfo,loanInfo);
            loanInfoRepository.save(loanInfo);

            //Require to get 6 months transaction from RepaymentInfo
            //6 months delayed info from RepaymentInfo to get credit score
            MicroEntrepreneur microEntrepreneur = creditScoreGenerator.getCreditScore(repaymentInfo);
            if (microEntrepreneur != null) {
                microEntrepreneurRepository.save(microEntrepreneur);
            }


            return repaymentInfo;
        }
        else{
            throw new RepaymentInfoNotFound("No loan exists for this repayment, LoanInfo - [ID = "+repaymentInfo.getLoanId()+"  ]");
        }
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

    @Override
    public RepaymentInfo getRepaymentInfoById(String id) throws Exception {
       /* Optional<RepaymentInfo> byId = repository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else*/
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
