package com.dbspshift.greenpark.micfin.reactiveservices;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.exceptions.MFINotFoundException;
import com.dbspshift.greenpark.micfin.reactiverepo.MFIReactiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Consumer;

import static com.dbspshift.greenpark.micfin.Others.MicFinPropererties.MFI_ID_BEG;

@Service
public class MFIServiceReactiveImpl implements MFIReactiveService {

    @Autowired
    MFIReactiveRepo repository;

    @Override
    public Mono<MFI> registerMFI(MFI mfi) throws Exception {


        return
        Mono.just(mfi.getCompanyName())
        .flatMap(repository::findByCompanyName).flatMap(tempmfi -> {
            if (tempmfi.getCompanyName().length() > 0) {
                return Mono.error(new MFINotFoundException("MFI is already registered - [CompanyName = " + mfi.getCompanyName() + "  ]"));
            } else {
                //Else part is required to make Mono.error return Mono of Mono<MFI> instead of Mono<Object>
                return Mono.just(new MFI());
            }

        })
        .switchIfEmpty(
            repository.getMaxMfiId()
            .map(x -> getNextMFISequence(x))
            .map(x ->
                {
                    mfi.setMfiId(x);
                    return mfi;
                }
            )
            .flatMap(repository::save)
         );

    }

    public boolean isDuplicateMfi(MFI mfi) throws Exception{
        Mono<MFI> byMfiId = repository.findByCompanyName(mfi.getCompanyName());
        return byMfiId.blockOptional().isPresent();

    }

    public String getNextMFISequence(){
        Optional<Integer> maxMfiId = repository.getMaxMfiId().blockOptional();
        String newMfiId = "";
        if(maxMfiId.isPresent()){
            Integer integer = maxMfiId.get();
            newMfiId = MFI_ID_BEG + String.valueOf(integer + 1);
        }
        else{
            newMfiId = MFI_ID_BEG + "1";
        }

        return newMfiId;
    }

    public String getNextMFISequence(Integer integer){
        String newMfiId = "";
        newMfiId = MFI_ID_BEG + String.valueOf(integer + 1);
        return newMfiId;
    }

    @Override
    public Mono<MFI> getMFIById(String id) throws Exception {
        return Mono.just(id)
                .flatMap(repository::findByMfiId)
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


/*Mono<Integer> maxMfiId = repository.getMaxMfiId();
                Mono<String> newMfiId = maxMfiId.map(x -> getNextMFISequence(x));
                Mono<MFI> mfiWithNewId = newMfiId.map(x -> {
                            mfi.setMfiId(x);
                            return mfi;
                        }
                );
                Mono<MFI> returnMono = mfiWithNewId.flatMap(repository::save);
                return returnMono;*/



        /*return repository.findByCompanyName(mfi.getCompanyName())
                .switchIfEmpty(repository.save(mfi));*/

       /* repository.findByCompanyName(mfi.getCompanyName()).subscribe(
                successvalue -> new MFINotFoundException("MFI is already registered - [CompanyName = "+mfi.getCompanyName()+"  ]"),
                error -> repository.save(mfi)
        );*/
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

//return(repository.save(mfi));


        /*if(isDuplicateMfi(mfi)){
            throw new MFINotFoundException("MFI is already registered - [CompanyName = "+mfi.getCompanyName()+"  ]");
        }*/


//String nextMFISequence = getNextMFISequence();