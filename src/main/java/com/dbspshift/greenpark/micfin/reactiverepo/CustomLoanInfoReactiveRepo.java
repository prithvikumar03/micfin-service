package com.dbspshift.greenpark.micfin.reactiverepo;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CustomLoanInfoReactiveRepo<T,Id> {

    public Mono<LoanInfo> findByLoanId(String loanId);

    public Mono<LoanInfo> findByMicroEntrepreneurId(String microEntrepreneurId);

    public Flux<LoanInfo> findByMfiId(String mfiId);

    public Flux<LoanSchedule> getLoanSchedule(String loanId);

    public Flux<LoanInfo> findByMfiIdMicroEntrepreneurId(String mfiId, String microEntrepreneurId);

    public Mono<Integer> getMaxLoanId();
}
