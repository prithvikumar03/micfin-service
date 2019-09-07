package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

public class CustomLoanInfoRepositoryImpl implements CustomLoanInfoRepository<LoanInfo,String> {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Optional<LoanInfo> findByLoanId(String loanId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("loanId").regex("^"+loanId));
        List<LoanInfo> loanInfo = mongoTemplate.find(query,LoanInfo.class);
        return Optional.of(loanInfo.get(0));
        //return loanInfo.size()>0?loanInfo.get(0):null;
    }

    @Override
    public Optional<LoanInfo> findByMicroEntrepreneurId(String microEntrepreneurId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("microEntrepreneurId").regex("^"+microEntrepreneurId));
        List<LoanInfo> loanInfo = mongoTemplate.find(query,LoanInfo.class);
        return loanInfo.size()>0?Optional.of(loanInfo.get(0)):Optional.empty();
    }

    @Override
    public Optional<List<LoanInfo>> findByMfiId(String mfiId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mfiId").regex("^"+mfiId));
        List<LoanInfo> loanInfo = mongoTemplate.find(query,LoanInfo.class);
        return Optional.of(loanInfo);
    }

    @Override
    public Optional<List<LoanSchedule>> getLoanSchedule(String loanId) {
        Optional<LoanInfo> byLoanId = findByLoanId(loanId);
        if(byLoanId.isPresent()){
            LoanInfo loanInfo = byLoanId.get();
            List<LoanSchedule> listLoanSchedule = loanInfo.getListLoanSchedule();
            return Optional.ofNullable(listLoanSchedule);
        }
        return Optional.empty();
    }
}
