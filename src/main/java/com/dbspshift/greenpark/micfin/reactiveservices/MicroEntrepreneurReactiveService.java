package com.dbspshift.greenpark.micfin.reactiveservices;

import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MicroEntrepreneurReactiveService {
    public Mono<MicroEntrepreneur> registerMicroEntrepreneur(MicroEntrepreneur microEntrepreneur) throws Exception;

    public Mono<MicroEntrepreneur> getMicroEntrepreneurByMicroEntrepreneurId(String MicroEntrepreneurId) throws Exception;

    public Flux<MicroEntrepreneur> getAllMicroEntrepreneursByMFIId(String id) throws Exception;

    public Mono<MicroEntrepreneur> updateMicroEntrepreneur(MicroEntrepreneur microEntrepreneur) throws Exception;

    public Mono<Void> deleteMicroEntrepreneur(String id) throws Exception;
}
