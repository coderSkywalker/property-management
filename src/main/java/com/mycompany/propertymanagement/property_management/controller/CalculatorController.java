package com.mycompany.propertymanagement.property_management.controller;

import com.mycompany.propertymanagement.property_management.dto.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @GetMapping("/add")
    public Double add(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }
    @GetMapping("/sub/{num1}/{num2}")
    public Double subtract(@PathVariable("num1") Double numberOne, @PathVariable() Double num2) {
        return numberOne - num2;
    }
    @PostMapping("/mul")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDTO calculatorDTO) {
        Double result = calculatorDTO.getNumberOne()
                * calculatorDTO.getNumberTwo()
                * calculatorDTO.getNumberThree()
                * calculatorDTO.getNumberFour();

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
