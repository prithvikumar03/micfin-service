package com.dbspshift.greenpark.micfin.reactiverepo;

import com.dbspshift.greenpark.micfin.beans.MFI;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CustomMFIReactiveRepo<T,Id> {

    Flux<MFI> findByNameStartingWith(String regexp);

    public Mono<MFI> findByMfiId(String mfiId);

    public Mono<Integer> getMaxMfiId();

    public Mono<MFI> findByCompanyName(String companyName);
}
