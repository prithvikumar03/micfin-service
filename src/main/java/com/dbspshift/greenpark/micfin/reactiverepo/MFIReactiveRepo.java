package com.dbspshift.greenpark.micfin.reactiverepo;

import com.dbspshift.greenpark.micfin.beans.MFI;
//import com.dbspshift.greenpark.micfin.repository.CustomMFIRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;

/**
 * Created by gayathrig on 24/07/2019.
 */

public interface MFIReactiveRepo extends ReactiveMongoRepository<MFI,String>,CustomMFIReactiveRepo<MFI,String> {

}
