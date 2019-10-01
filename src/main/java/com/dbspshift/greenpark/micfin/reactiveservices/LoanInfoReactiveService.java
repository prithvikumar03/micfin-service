package com.dbspshift.greenpark.micfin.reactiveservices;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import com.dbspshift.greenpark.micfin.exceptions.LoanInfoNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LoanInfoReactiveService {

    public Mono<LoanInfo> registerLoanInfo(LoanInfo LoanInfo) throws Exception;

    public Mono<LoanInfo> getLoanInfoByLoanId(String id) throws Exception;

    public Flux<LoanInfo> getAllLoanInfos() throws Exception;

    public Mono<LoanInfo> updateLoanInfo(LoanInfo LoanInfo) throws Exception;

    public Mono<String> deleteLoanInfo(String id) throws Exception;

    public Flux<LoanInfo> getAllLoanInfosForMFI(String mfiId) throws Exception;

    Flux<LoanSchedule> getLoanScheduleForLoanId(String loanId) throws LoanInfoNotFoundException;

    Flux<LoanInfo> getAllLoanInfosForMfiAndMicroEntrepreneur(String mfiId, String microEntId)  throws Exception;
}
