package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

public class CustomeMicroEntrepreneurRepositoryImpl implements CustomeMicroEntrepreneurRepository<LoanInfo,String>  {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Optional<MicroEntrepreneur> findByMicroEntrepreneurId(String microEntrepreneurId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("microEntrepreneurId").regex("^"+microEntrepreneurId));
        List<MicroEntrepreneur> microEntrepreneurs = mongoTemplate.find(query,MicroEntrepreneur.class);
        return Optional.of(microEntrepreneurs.get(0));
        //return microEntrepreneurs.size()>0?microEntrepreneurs.get(0):null;
    }
}
