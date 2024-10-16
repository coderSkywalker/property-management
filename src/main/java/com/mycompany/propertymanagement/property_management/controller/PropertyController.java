package com.mycompany.propertymanagement.property_management.controller;

import com.mycompany.propertymanagement.property_management.dto.PropertyDTO;
import com.mycompany.propertymanagement.property_management.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping(path = "/hello", consumes = "application/json", produces = "application/json")
    public String sayHello() {
        return "Hello";
    }
    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
        propertyDTO = propertyService.saveProperty(propertyDTO);
        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {

        return new ResponseEntity<>(propertyService.getAllProperties(), HttpStatus.OK);
    }

    @PutMapping("/properties/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable("id") Long propertyId) {

        return new ResponseEntity<>(propertyService.updateProperty(propertyDTO, propertyId), HttpStatus.OK);
    }

    @PatchMapping("/properties/update-description/{Id}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable("Id") Long propertyId) {

        return new ResponseEntity<>(propertyService.updatePropertyDescription(propertyDTO, propertyId), HttpStatus.OK);
    }

    @PatchMapping("properties/update-price/{Id}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable("Id") Long propertyId) {

        return new ResponseEntity<>(propertyService.updatePropertyPrice(propertyDTO, propertyId), HttpStatus.OK);
    }

    @DeleteMapping("properties/delete/{propertyId}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
