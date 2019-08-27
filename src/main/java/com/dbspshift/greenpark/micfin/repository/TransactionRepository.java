package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction,String> {

}
