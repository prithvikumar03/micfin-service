package com.dbspshift.greenpark.micfin.integration.repository;

import com.dbspshift.greenpark.micfin.beans.Address;
import com.dbspshift.greenpark.micfin.beans.MFI;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gayathrig on 24/07/2019.
 */

@Repository
public interface AddressRepository extends ReactiveMongoRepository<Address,String> {
}
