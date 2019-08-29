package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
