package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.Others.CreditScoreGenerator;
import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import com.dbspshift.greenpark.micfin.repository.LoanInfoRepository;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurRepository;
import com.dbspshift.greenpark.micfin.repository.RepaymentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RepaymentInfoServiceImpl implements RepaymentInfoService {

    //RepaymentInfoRepository repository;
    @Autowired
    LoanInfoRepository loanInfoRepository;
    @Autowired
    CreditScoreGenerator creditScoreGenerator;
    @Autowired
    MicroEntrepreneurRepository microEntrepreneurRepository;

    @Override
    public RepaymentInfo registerRepaymentInfo(RepaymentInfo repaymentInfo) throws Exception {
        //List<RepaymentInfo> allRepaymentInfoByMicroEntrepreneurId = getAllRepaymentInfoByMicroEntrepreneurId(repaymentInfo.getMicroEntrepreneurId());


        ///*'LIMIT_BAL', 'SEX', 'EDUCATION', 'MARRIAGE', 'AGE', from MicroEntrepreneur
        //loanInfoRepository.f
        LoanInfo loanInfo = loanInfoRepository.findByLoanId(repaymentInfo.getLoanId());
        //List<RepaymentInfo> repaymentInfoList = loanInfo.getRepaymentInfoList();
        loanInfo.addToRepaymentInfoList(repaymentInfo);
        //RepaymentInfo save = repository.save(repaymentInfo);
        loanInfoRepository.save(loanInfo);

        //Require to get 6 months transaction from RepaymentInfo
        //6 months delayed info from RepaymentInfo to get credit score
        MicroEntrepreneur microEntrepreneur = creditScoreGenerator.getCreditScore(repaymentInfo);
        if(microEntrepreneur!=null) {
            microEntrepreneurRepository.save(microEntrepreneur);
        }
        return repaymentInfo;
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
        //Predicate<RepaymentInfo> predFilterByMeId = rp -> rp.getMicroEntrepreneurId().equals(id);
        //List<RepaymentInfo> collect = repository.findAll().stream().filter(p -> predFilterByMeId.test(p)).collect(Collectors.toList());
        LoanInfo loanInfo = loanInfoRepository.findByMicroEntrepreneurId(microEntrepreneurId);
        return loanInfo.getRepaymentInfoList();
    }
}
