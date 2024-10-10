package com.mycompany.propertymanagement.property_management.service.impl;

import com.mycompany.propertymanagement.property_management.converter.UserConverter;
import com.mycompany.propertymanagement.property_management.dto.UserDTO;
import com.mycompany.propertymanagement.property_management.entity.UserEntity;
import com.mycompany.propertymanagement.property_management.exception.BusinessException;
import com.mycompany.propertymanagement.property_management.exception.ErrorModel;
import com.mycompany.propertymanagement.property_management.repository.UserRepository;
import com.mycompany.propertymanagement.property_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;
    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (optionalUserEntity.isPresent()) {
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("WRONG_EMAIL");
            errorModel.setMessage("User with email: " + userDTO.getOwnerEmail() + " already exists");
            errorModels.add(errorModel);

            throw new BusinessException(errorModels);
        }

        return userConverter.convertEntityToDTO(userRepository.save(userConverter.convertDTOtoEntity(userDTO)));
    }

    @Override
    public UserDTO login(String email, String password) {

        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        if (optionalUserEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());
        }else {
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorect email or password");
            errorModels.add(errorModel);

            throw new BusinessException(errorModels);

        }

        return userDTO;
    }
}
