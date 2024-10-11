package com.mycompany.propertymanagement.property_management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
    private String ownerName;
    @NotNull(message = "Owner Email is mandatory")
    @NotEmpty(message = "Owner Email can not be empty")
    @Size(min = 5, max = 50, message = "Owner Email should be between 5 and 50 characters long")
    private String ownerEmail;
    private String phone;
    @NotNull(message = "Password must be given")
    @NotEmpty(message = "Password can not be empty")
    private String password;
}
