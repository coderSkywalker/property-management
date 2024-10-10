package com.mycompany.propertymanagement.property_management.service.impl;

import com.mycompany.propertymanagement.property_management.converter.UserConverter;
import com.mycompany.propertymanagement.property_management.dto.UserDTO;
import com.mycompany.propertymanagement.property_management.entity.UserEntity;
import com.mycompany.propertymanagement.property_management.repository.UserRepository;
import com.mycompany.propertymanagement.property_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;
    @Override
    public UserDTO register(UserDTO userDTO) {

        return userConverter.convertEntityToDTO(userRepository.save(userConverter.convertDTOtoEntity(userDTO)));
    }

    @Override
    public UserDTO login(String email, String password) {

        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        if (optionalUserEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());
        }

        return userDTO;
    }
}
