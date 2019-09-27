package com.dbspshift.greenpark.micfin.reactiverepo;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomLoanInfoReactiveRepoImpl implements CustomLoanInfoReactiveRepo<LoanInfo,String> {

    @Autowired
    ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<LoanInfo> findByLoanId(String loanId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("loanId").regex("^"+loanId));
        Flux<LoanInfo> loanInfoFlux = mongoTemplate.find(query, LoanInfo.class);
        return loanInfoFlux.next();
    }

    @Override
    public Mono<LoanInfo> findByMicroEntrepreneurId(String microEntrepreneurId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("microEntrepreneurId").regex("^"+microEntrepreneurId));
        Flux<LoanInfo> loanInfoFlux = mongoTemplate.find(query, LoanInfo.class);
        return loanInfoFlux.next();
    }

    @Override
    public Flux<LoanInfo> findByMfiId(String mfiId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mfiId").regex("^"+mfiId));
        Flux<LoanInfo> loanInfoFlux = mongoTemplate.find(query, LoanInfo.class);
        return loanInfoFlux;
    }

    @Override
    public Flux<LoanSchedule> getLoanSchedule(String loanId) {
        //Mono<LoanInfo> byLoanId = findByLoanId(loanId);
        Optional<LoanInfo> byLoanId = findByLoanId(loanId).blockOptional();
        if(byLoanId.isPresent()){
            LoanInfo loanInfo = byLoanId.get();
            List<LoanSchedule> listLoanSchedule = loanInfo.getListLoanSchedule();
            //return Optional.ofNullable(listLoanSchedule);
            return Flux.fromIterable(listLoanSchedule);
        }
        return Flux.empty();
    }

    @Override
    public Flux<LoanInfo> findByMfiIdMicroEntrepreneurId(String mfiId, String microEntrepreneurId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mfiId").regex("^"+mfiId).and("microEntrepreneurId").regex("^"+microEntrepreneurId));
        Flux<LoanInfo> loanInfoFlux = mongoTemplate.find(query, LoanInfo.class);
        return loanInfoFlux;
    }

    @Override
    public Mono<Integer> getMaxLoanId() {
        String queryRegex = "^LN[0-9]{1,3}";
        Pattern p = Pattern.compile("\\d+");

        Query query = new Query();
        query.addCriteria(Criteria.where("loanId").regex(queryRegex));

        Flux<String> distinct = mongoTemplate.findDistinct(query, "loanId", "LoanInfo", String.class);
        System.out.println(distinct);
        Mono<Integer> reduce = distinct.map(y -> p.matcher(y)).filter(t -> t.find())
                .map(z -> Integer.parseInt(z.group())).reduce(Integer::compare);

        return reduce;
    }
}
