package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MFI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;
import java.util.Optional;

/**
 * Use this for complex MongoDB queries
 */
public class CustomMFIRepositoryImpl implements CustomMFIRepository<MFI,String>{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<MFI> findByNameStartingWith(String regexp) {
       return null;
    }

    /*@Override
    public List<MFI> findByNameStartingWith(String regexp) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex("^"+regexp));
        Flux<MFI> mfiList = repository.find(query,MFI.class);
        return mfiList;
        return null;
    }*/

/*    @Override
    public MFI findByMicroEntrepreneurId(String microEntrepreneurId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("microEntrepreneurId").regex("^"+microEntrepreneurId));
        List<MFI> mfi = mongoTemplate.find(query,MFI.class);
        return mfi.size()>0?mfi.get(0):null;
    }*/

    @Override
    public Optional<MFI> findByMfiId(String mfiId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mfiId").regex("^"+mfiId));
        List<MFI> mfi = mongoTemplate.find(query,MFI.class);
        Optional<MFI> mfiOptional = Optional.of(mfi.get(0));
        //return mfi.size()>0?mfi.get(0):null;
        return mfiOptional;
    }

}

