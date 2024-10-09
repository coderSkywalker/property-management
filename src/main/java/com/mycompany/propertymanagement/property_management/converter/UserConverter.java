package com.mycompany.propertymanagement.property_management.converter;

import com.mycompany.propertymanagement.property_management.dto.UserDTO;
import com.mycompany.propertymanagement.property_management.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertDTOtoEntity(UserDTO userDto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setOwnerName(userDto.getOwnerName());
        userEntity.setOwnerEmail(userDto.getOwnerEmail());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setPassword(userDto.getPassword());

        return userEntity;
    }

    public UserDTO convertEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setOwnerEmail(userEntity.getOwnerEmail());
        userDTO.setId(userEntity.getId());
        userDTO.setOwnerName(userEntity.getOwnerName());
        userDTO.setPassword(userDTO.getPassword());
        userDTO.setPhone(userEntity.getPhone());

        return userDTO;
    }
}
