package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRole(String role);
}
