package com.dbspshift.greenpark.micfin.reactiverepo;

import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MicroEntrepreneurReactiveRepo extends ReactiveMongoRepository<MicroEntrepreneur,String>, CustomeMicroEntrepreneurReactiveRepo<MicroEntrepreneur,String> {
}
