package com.dbspshift.greenpark.micfin.reactiverepo;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomeMicroEntrepreneurReactiveRepoImpl implements CustomeMicroEntrepreneurReactiveRepo<MicroEntrepreneur,String>  {

    @Autowired
    ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<MicroEntrepreneur> findByMicroEntrepreneurId(String microEntrepreneurId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("microEntrepreneurId").regex("^"+microEntrepreneurId));
        Flux<MicroEntrepreneur> microEntrepreneurFlux = mongoTemplate.find(query, MicroEntrepreneur.class);
        return microEntrepreneurFlux.next();
    }

    @Override
    public Flux<MicroEntrepreneur> findAllMicroEntrepreneursByMFIId(String mfiId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mfiId").regex("^"+mfiId));
        Flux<MicroEntrepreneur> microEntrepreneurFlux = mongoTemplate.find(query, MicroEntrepreneur.class);
        return microEntrepreneurFlux;
    }

    @Override
    public Mono<MicroEntrepreneur> findByMicroEntrepreneurFirstName(String microEntrepreneurFirstName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").regex(microEntrepreneurFirstName));
        Flux<MicroEntrepreneur> microEntrepreneurFlux = mongoTemplate.find(query, MicroEntrepreneur.class);
        return microEntrepreneurFlux.next();
    }

    @Override
    public Mono<MicroEntrepreneur> findByMicroEntrepreneurLastName(String microEntrepreneurLastName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastName").regex(microEntrepreneurLastName));
        Flux<MicroEntrepreneur> microEntrepreneurFlux = mongoTemplate.find(query, MicroEntrepreneur.class);
        return microEntrepreneurFlux.next();
    }

    @Override
    public Mono<Integer> getMaxMicroEntrepreneurId() {
        String queryRegex = "^ME[0-9]{1,3}";
        Pattern p = Pattern.compile("\\d+");

        Query query = new Query();
        query.addCriteria(Criteria.where("microEntrepreneurId").regex(queryRegex));

        Flux<String> distinct = mongoTemplate.findDistinct(query, "microEntrepreneurId", "MicroEntrepreneur", String.class);
        System.out.println(distinct);
        Mono<Integer> reduce = distinct.map(y -> p.matcher(y)).filter(t -> t.find())
                .map(z -> Integer.parseInt(z.group())).reduce(Integer::compare);

        return reduce;
    }


}
