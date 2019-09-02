package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MicroEntrepreneurReactiveRepository extends ReactiveMongoRepository<MicroEntrepreneur,String> {
}
