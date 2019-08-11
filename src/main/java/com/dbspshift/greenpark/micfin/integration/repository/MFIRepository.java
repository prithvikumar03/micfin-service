package com.dbspshift.greenpark.micfin.integration.repository;

import com.dbspshift.greenpark.micfin.beans.MFI;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;

/**
 * Created by gayathrig on 24/07/2019.
 */

@Repository
public interface MFIRepository extends ReactiveMongoRepository<MFI,String>,CustomMFIRepository<MFI,String> {
}
