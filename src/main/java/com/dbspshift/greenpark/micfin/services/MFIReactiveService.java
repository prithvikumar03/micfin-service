package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MFI;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MFIReactiveService {
    public Mono<MFI> registerMFI(MFI mfi) throws Exception;

    public Mono<MFI> getMFIById(String id) throws Exception;

    public Flux<MFI> getAllMFIs() throws Exception;

    public Mono<MFI> updateMFI(MFI mfi) throws Exception;

    public Mono<Void> deleteMFI(String id) throws Exception;
}
