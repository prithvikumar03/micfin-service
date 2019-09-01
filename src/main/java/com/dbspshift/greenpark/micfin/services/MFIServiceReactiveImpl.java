package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.exceptions.MFINotFoundException;
import com.dbspshift.greenpark.micfin.repository.MFIReactiveRepository;
import com.dbspshift.greenpark.micfin.repository.MFIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class MFIServiceReactiveImpl implements MFIReactiveService {

    @Autowired
    MFIReactiveRepository repository;

    @Override
    public Mono<MFI> registerMFI(MFI mfi) throws Exception {
        return(repository.save(mfi));
    }

    @Override
    public Mono<MFI> getMFIById(String id) throws Exception {
        return Mono.just(id)
                .flatMap(repository::findById)
                .switchIfEmpty(Mono.error(new MFINotFoundException("Could not find details for MFI - [ID = "+id+"  ]")));

    }

    @Override
    public Flux<MFI> getAllMFIs() throws Exception {
        return repository.findAll();
    }

    @Override
    public Mono<MFI> updateMFI(MFI mfi) throws Exception {
        Mono<MFI> existingMFI = getMFIById(mfi.getId());
        existingMFI.flatMap(repository::save);
        return existingMFI;
    }

    @Override
    public Mono<Void> deleteMFI(String id){
            return repository.deleteById(id).switchIfEmpty(Mono.error(new IllegalArgumentException()));
    }


}
