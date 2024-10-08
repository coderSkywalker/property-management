package com.mycompany.propertymanagement.property_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class CalculatorDTO {

    private Double numberOne;
    private Double numberTwo;
    private Double numberThree;
    @JsonProperty("lastNumber")
    private Double numberFour;

    public Double getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(Double numberOne) {
        this.numberOne = numberOne;
    }

    public Double getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(Double numberTwo) {
        this.numberTwo = numberTwo;
    }

    public Double getNumberThree() {
        return numberThree;
    }

    public void setNumberThree(Double numberThree) {
        this.numberThree = numberThree;
    }

    public Double getNumberFour() {
        return numberFour;
    }

    public void setNumberFour(Double numberFour) {
        this.numberFour = numberFour;
    }
}
