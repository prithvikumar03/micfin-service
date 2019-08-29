package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepaymentInfoRepository extends MongoRepository<RepaymentInfo,String> {

}
