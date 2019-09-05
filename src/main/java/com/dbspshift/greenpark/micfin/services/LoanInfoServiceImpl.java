package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.exceptions.LoanInfoNotFoundException;
import com.dbspshift.greenpark.micfin.repository.LoanInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class LoanInfoServiceImpl implements LoanInfoService{

    @Autowired
    LoanInfoRepository loanInfoRepository;

    @Override
    public LoanInfo registerLoanInfo(LoanInfo loanInfo) throws Exception {
        return loanInfoRepository.insert(loanInfo);
    }

    @Override
    public LoanInfo getLoanInfoById(String loanId) throws Exception {
        Optional<LoanInfo> byId = loanInfoRepository.findAll().stream().filter(li -> li.getLoanId().equals(loanId.trim())).findFirst();
        //Optional<LoanInfo> byId = loanInfoRepository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else
            throw new LoanInfoNotFoundException("Could not find details for LoanInfo - [ID = "+loanId+"  ]");
    }

    @Override
    public List<LoanInfo> getAllLoanInfos() throws Exception {
        return loanInfoRepository.findAll();
    }

    @Override
    public LoanInfo updateLoanInfo(LoanInfo loanInfo) throws Exception {
        return loanInfoRepository.save(loanInfo);
    }

    @Override
    public String deleteLoanInfo(String id) throws Exception {
        try {
            loanInfoRepository.deleteById(id);
            return "success";
        }catch(Exception e){
            return "failed";
        }
    }

    @Override
    public List<LoanInfo> getAllLoanInfosForMFI(String mfiId) throws Exception {
        Predicate<LoanInfo> predFilterByMeId = rp -> rp.getMfiId().equals(mfiId);
        List<LoanInfo> collect = loanInfoRepository.findAll().stream().filter(p -> predFilterByMeId.test(p)).collect(Collectors.toList());
        return collect;
    }
}
