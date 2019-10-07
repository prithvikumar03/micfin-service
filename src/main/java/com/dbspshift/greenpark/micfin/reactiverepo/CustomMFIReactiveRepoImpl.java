package com.dbspshift.greenpark.micfin.reactiverepo;

import com.dbspshift.greenpark.micfin.beans.MFI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use this for complex MongoDB queries
 */
public class CustomMFIReactiveRepoImpl implements CustomMFIReactiveRepo<MFI,String>{

    @Autowired
    ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<MFI> findByNameStartingWith(String regexp) {
       return null;
    }

    @Override
    public Mono<MFI> findByMfiId(String mfiId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mfiId").regex("^"+mfiId));
        Flux<MFI> mfiFlux = mongoTemplate.find(query, MFI.class);
        return mfiFlux.next();
    }

    public Mono<Integer> getMaxMfiId(){
        //List<String> elements = new ArrayList<>();

        String queryRegex = "^MFI[0-9]{1,5}";
        Pattern p = Pattern.compile("\\d+");
        Query query = new Query();
        query.addCriteria(Criteria.where("mfiId").regex(queryRegex));

        Flux<String> distinct = mongoTemplate.findDistinct(query, "mfiId", "MFI", String.class);
        Flux<Integer> map = distinct.map(y -> p.matcher(y)).filter(t -> t.find())
                .map(z -> Integer.parseInt(z.group()));
        Mono<Integer> reduce = map.reduce(0,Math::max);

/*        System.out.println(distinct);
        distinct.log().subscribe(elements::add);
        Optional<Integer> maxId = elements.stream().map(y -> p.matcher(y)).filter(t -> t.find())
                .map(z -> Integer.parseInt(z.group())).max(Integer::compare);*/
        return reduce;
    }

    @Override
    public Mono<MFI> findByCompanyName(String companyName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("companyName").regex(companyName));
        Flux<MFI> mfiFlux = mongoTemplate.find(query, MFI.class);
        return mfiFlux.next();
    }
}