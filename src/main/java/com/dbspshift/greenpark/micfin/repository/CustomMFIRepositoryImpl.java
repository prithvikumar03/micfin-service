package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.MFI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public Optional<MFI> findByMfiId(String mfiId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mfiId").regex("^"+mfiId));
        List<MFI> mfi = mongoTemplate.find(query,MFI.class);
        return mfi.size()>0? Optional.of(mfi.get(0)):Optional.empty();
    }

    public Optional<Integer> getMaxMfiId(){
        String queryRegex = "^MFI[0-9]{1,5}";
        Pattern p = Pattern.compile("\\d+");

        Query query = new Query();
        query.addCriteria(Criteria.where("mfiId").regex(queryRegex));

        List<String> distinct = mongoTemplate.findDistinct(query, "mfiId", "MFI", String.class);
        System.out.println(distinct);
        Optional<Integer> maxId = distinct.stream().map(y -> p.matcher(y)).filter(t -> t.find())
                .map(z -> Integer.parseInt(z.group())).max(Integer::compare);

        return maxId;
    }

    @Override
    public Optional<MFI> findByCompanyName(String companyName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("companyName").regex(companyName));
        List<MFI> mfi = mongoTemplate.find(query,MFI.class);
        return mfi.size()>0? Optional.of(mfi.get(0)):Optional.empty();
    }


}

/* @Override
    public List<MFI> findByNameStartingWith(String regexp) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex("^"+regexp));
        Flux<MFI> mfiList = repository.find(query,MFI.class);
        return mfiList;
        return null;
    }

    @Override
    public MFI findByMicroEntrepreneurId(String microEntrepreneurId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("microEntrepreneurId").regex("^"+microEntrepreneurId));
        List<MFI> mfi = mongoTemplate.find(query,MFI.class);
        return mfi.size()>0?mfi.get(0):null;
    }*/



        /*List<String> listMfiIds = mongoTemplate.findDistinct("mfiId", MFI.class, String.class);
        Optional<Integer> maxId = listMfiIds.stream().filter(x -> Pattern.matches(queryRegex,x))
                .map(y ->
                {
                    Matcher matcher = p.matcher(y);
                    return matcher.find()?Integer.parseInt(matcher.group()):-1;
                })
                //.max((i,j) -> i>j?i:j );
                .max(Integer::compare);*/