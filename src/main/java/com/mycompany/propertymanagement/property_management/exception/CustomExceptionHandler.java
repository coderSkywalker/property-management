package com.mycompany.propertymanagement.property_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException) {

        return new ResponseEntity<>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidationException(MethodArgumentNotValidException manve) {

        List<ErrorModel> errorModels = new ArrayList<>();
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(fieldError.getCode());
            errorModel.setMessage(fieldError.getDefaultMessage());
            errorModels.add(errorModel);
        }

        return new ResponseEntity<>(errorModels, HttpStatus.BAD_REQUEST);
    }
}
