package com.mycompany.propertymanagement.property_management.service.impl;

import com.mycompany.propertymanagement.property_management.converter.PropertyConverter;
import com.mycompany.propertymanagement.property_management.dto.PropertyDTO;
import com.mycompany.propertymanagement.property_management.entity.PropertyEntity;
import com.mycompany.propertymanagement.property_management.repository.PropertyRepository;
import com.mycompany.propertymanagement.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity propertyEntity = propertyRepository.save(propertyConverter.convertDTOtoEntity(propertyDTO));

        return propertyConverter.convertEntityToDTO(propertyEntity);
    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        return ((List<PropertyEntity>) propertyRepository.findAll()).stream()
                .map(entity -> propertyConverter.convertEntityToDTO(entity)).collect(Collectors.toList());
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        if (propertyRepository.findById(propertyId).isPresent()) {
            PropertyEntity propertyEntity = propertyConverter.convertDTOtoEntity(propertyDTO);
            propertyEntity.setId(propertyId);

            return propertyConverter.convertEntityToDTO(propertyRepository.save(propertyEntity));
        }
        return propertyConverter.convertEntityToDTO(propertyRepository.save(propertyConverter.convertDTOtoEntity(propertyDTO)));
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyEntityOptional = propertyRepository.findById(propertyId);
        if (propertyEntityOptional.isEmpty()) {
            throwException(propertyId);
        }
        PropertyEntity propertyEntity = propertyEntityOptional.get();
        propertyEntity.setDescription(propertyDTO.getDescription());

        return propertyConverter.convertEntityToDTO(propertyRepository.save(propertyEntity));
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> propertyEntityOptional = propertyRepository.findById(propertyId);
        if (propertyEntityOptional.isEmpty()) {
            throwException(propertyId);
        }

        PropertyEntity propertyEntity = propertyEntityOptional.get();
        propertyEntity.setPrice(propertyDTO.getPrice());

        return propertyConverter.convertEntityToDTO(propertyRepository.save(propertyEntity));

    }

    @Override
    public void deleteProperty(Long propertyId) {

        if (propertyRepository.findById(propertyId).isEmpty()) {
            throwException(propertyId);
        }
        propertyRepository.deleteById(propertyId);
    }

    private PropertyDTO throwException(Long propertyId) {
        throw new IllegalArgumentException("Entity with id: " + propertyId + " does not exists.");
    }
}
