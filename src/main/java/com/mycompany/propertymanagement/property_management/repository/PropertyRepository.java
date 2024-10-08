package com.mycompany.propertymanagement.property_management.repository;

import com.mycompany.propertymanagement.property_management.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
}
