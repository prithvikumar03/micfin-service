package com.dbspshift.greenpark.micfin.integration.repository;

import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MicroEntrepreneurRepository extends MongoRepository<MicroEntrepreneur,String> {
}
