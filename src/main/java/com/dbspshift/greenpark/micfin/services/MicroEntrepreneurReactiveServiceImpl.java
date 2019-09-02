package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.exceptions.MFINotFoundException;
import com.dbspshift.greenpark.micfin.exceptions.MicroEntrepreneurNotFoundException;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurReactiveRepository;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class MicroEntrepreneurReactiveServiceImpl implements MicroEntrepreneurReactiveService {

    private final Logger logger = LogManager.getLogger(MicroEntrepreneurReactiveServiceImpl.class);

    @Autowired
    MicroEntrepreneurReactiveRepository microEntrepreneurReactiveRepository;

    @Override
    public Mono<MicroEntrepreneur> registerMicroEntrepreneur(MicroEntrepreneur microEntrepreneur) throws Exception {
        return microEntrepreneurReactiveRepository.save(microEntrepreneur);
    }

    @Override
    public Mono<MicroEntrepreneur> getMicroEntrepreneurById(String id) throws Exception {
        return Mono.just(id)
                .flatMap(microEntrepreneurReactiveRepository::findById)
                .switchIfEmpty(Mono.error(new MicroEntrepreneurNotFoundException("Could not find details for MicroEntrepreneur  - [ID = "+id+"  ]")));
    }

    @Override
    public Flux<MicroEntrepreneur> getAllMicroEntrepreneursByMFIId(String mfiId) throws Exception {
        return microEntrepreneurReactiveRepository.findAll();
    }

    @Override
    public Mono<MicroEntrepreneur> updateMicroEntrepreneur(MicroEntrepreneur microEntrepreneur) throws Exception {
        Mono<MicroEntrepreneur> existingME = getMicroEntrepreneurById(microEntrepreneur.getId());
        existingME.flatMap(microEntrepreneurReactiveRepository::save);
        return existingME;
    }

    @Override
    public Mono<Void> deleteMicroEntrepreneur(String id){
        return microEntrepreneurReactiveRepository.deleteById(id).switchIfEmpty(Mono.error(new IllegalArgumentException()));
    }
}
