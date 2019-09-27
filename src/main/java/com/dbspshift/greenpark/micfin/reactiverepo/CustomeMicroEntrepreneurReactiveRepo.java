package com.dbspshift.greenpark.micfin.reactiverepo;

import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CustomeMicroEntrepreneurReactiveRepo<T,Id> {

    public Mono<MicroEntrepreneur> findByMicroEntrepreneurId(String microEntrepreneurId);

    public Flux<MicroEntrepreneur> findAllMicroEntrepreneursByMFIId(String microEntrepreneurId);

    public Mono<MicroEntrepreneur> findByMicroEntrepreneurFirstName(String microEntrepreneurFirstName);

    public Mono<MicroEntrepreneur> findByMicroEntrepreneurLastName(String microEntrepreneurLastName);

    public Mono<Integer> getMaxMicroEntrepreneurId();
}
