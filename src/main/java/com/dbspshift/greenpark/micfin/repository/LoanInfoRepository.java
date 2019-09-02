package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanInfoRepository extends MongoRepository<LoanInfo,String>, CustomLoanInfoRepository<LoanInfo, String>{
}
