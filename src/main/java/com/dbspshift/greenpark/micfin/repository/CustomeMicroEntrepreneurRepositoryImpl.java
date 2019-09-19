package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomeMicroEntrepreneurRepositoryImpl implements CustomeMicroEntrepreneurRepository<LoanInfo,String>  {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Optional<MicroEntrepreneur> findByMicroEntrepreneurId(String microEntrepreneurId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("microEntrepreneurId").regex("^"+microEntrepreneurId));
        List<MicroEntrepreneur> microEntrepreneurs = mongoTemplate.find(query,MicroEntrepreneur.class);
        //Optional<MicroEntrepreneur> microEntrepreneurOptional = Optional.of(microEntrepreneurs.get(0));
        //return microEntrepreneurOptional;
        return microEntrepreneurs.size()>0?Optional.of(microEntrepreneurs.get(0)):Optional.empty();
    }

    @Override
    public Optional<List<MicroEntrepreneur>> findAllMicroEntrepreneursByMFIId(String mfiId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mfiId").regex("^"+mfiId));
        List<MicroEntrepreneur> microEntrepreneurs = mongoTemplate.find(query,MicroEntrepreneur.class);
        return Optional.of(microEntrepreneurs);
    }

    @Override
    public Optional<MicroEntrepreneur> findByMicroEntrepreneurFirstName(String microEntrepreneurFirstName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").regex(microEntrepreneurFirstName));
        List<MicroEntrepreneur> mfi = mongoTemplate.find(query,MicroEntrepreneur.class);
        return mfi.size()>0? Optional.of(mfi.get(0)):Optional.empty();
    }

    @Override
    public Optional<MicroEntrepreneur> findByMicroEntrepreneurLastName(String microEntrepreneurLastName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastName").regex(microEntrepreneurLastName));
        List<MicroEntrepreneur> mfi = mongoTemplate.find(query,MicroEntrepreneur.class);
        return mfi.size()>0? Optional.of(mfi.get(0)):Optional.empty();
    }

    @Override
    public Optional<Integer> getMaxMicroEntrepreneurId() {
        String queryRegex = "^ME[0-9]{1,3}";
        Pattern p = Pattern.compile("\\d+");

        Query query = new Query();
        query.addCriteria(Criteria.where("microEntrepreneurId").regex(queryRegex));

        List<String> distinct = mongoTemplate.findDistinct(query, "microEntrepreneurId", "MicroEntrepreneur", String.class);
        System.out.println(distinct);
        Optional<Integer> maxId = distinct.stream().map(y -> p.matcher(y)).filter(t -> t.find())
                .map(z -> Integer.parseInt(z.group())).max(Integer::compare);

        return maxId;
    }


}
