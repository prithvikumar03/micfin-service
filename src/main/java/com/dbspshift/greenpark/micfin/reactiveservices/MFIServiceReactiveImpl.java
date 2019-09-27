package com.dbspshift.greenpark.micfin.reactiveservices;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.exceptions.MFINotFoundException;
import com.dbspshift.greenpark.micfin.reactiverepo.MFIReactiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static com.dbspshift.greenpark.micfin.Others.MicFinPropererties.MFI_ID_BEG;

@Service
public class MFIServiceReactiveImpl implements MFIReactiveService {

    @Autowired
    MFIReactiveRepo repository;

    @Override
    public Mono<MFI> registerMFI(MFI mfi) throws Exception {

        if(isDuplicateMfi(mfi)){
            throw new MFINotFoundException("MFI is already registered - [CompanyName = "+mfi.getCompanyName()+"  ]");
        }
        /*else {
            Mono<Integer> maxMfiId = repository.getMaxMfiId();
            String newMfiId = "";
            if(maxMfiId.sub()){
                Integer integer = maxMfiId.get();
                newMfiId = MFI_ID_BEG + String.valueOf(integer + 1);
            }
            else{
                newMfiId = MFI_ID_BEG + "1";
            }
            mfi.setMfiId(newMfiId);
            return (repository.save(mfi));
        }*/
        return(repository.save(mfi));
    }

    public boolean isDuplicateMfi(MFI mfi) throws Exception{
        Mono<MFI> byMfiId = repository.findByCompanyName(mfi.getCompanyName());
        return byMfiId.blockOptional().isPresent();

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
