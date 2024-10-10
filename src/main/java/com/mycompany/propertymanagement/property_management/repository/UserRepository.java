package com.mycompany.propertymanagement.property_management.repository;

import com.mycompany.propertymanagement.property_management.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByOwnerEmailAndPassword(String email, String password);
}
