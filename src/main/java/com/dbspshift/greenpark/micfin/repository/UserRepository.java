package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
