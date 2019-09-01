package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.MFI;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;

/**
 * Created by gayathrig on 24/07/2019.
 */

public interface MFIReactiveRepository extends ReactiveMongoRepository<MFI,String>,CustomMFIRepository<MFI,String>{
//      List<MFI> findByCompanyName(String companyName);
}
