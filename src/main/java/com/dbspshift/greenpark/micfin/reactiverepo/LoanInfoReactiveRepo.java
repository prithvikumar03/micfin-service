package com.dbspshift.greenpark.micfin.reactiverepo;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MFI;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LoanInfoReactiveRepo extends ReactiveMongoRepository<LoanInfo,String>,CustomLoanInfoReactiveRepo<LoanInfo,String> {
}
