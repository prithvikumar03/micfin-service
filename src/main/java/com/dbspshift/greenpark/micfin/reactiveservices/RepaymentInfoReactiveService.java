package com.dbspshift.greenpark.micfin.reactiveservices;

import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RepaymentInfoReactiveService {

    public Mono<RepaymentInfo> registerRepaymentInfo(RepaymentInfo repaymentInfo) throws Exception;

    public Mono<RepaymentInfo> getRepaymentInfoById(String id) throws Exception;

    public Flux<RepaymentInfo> getAllRepaymentInfoByMicroEntrepreneurId(String id) throws Exception;

    //public String deleteRepaymentInfo(String id) throws Exception;
}
