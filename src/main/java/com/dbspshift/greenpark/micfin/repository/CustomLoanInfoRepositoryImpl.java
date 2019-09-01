package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CustomLoanInfoRepositoryImpl implements CustomLoanInfoRepository<LoanInfo,String> {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public LoanInfo findByLoanId(String loanId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("loanId").regex("^"+loanId));
        List<LoanInfo> loanInfo = mongoTemplate.find(query,LoanInfo.class);
        return loanInfo.size()>0?loanInfo.get(0):null;
    }
}
