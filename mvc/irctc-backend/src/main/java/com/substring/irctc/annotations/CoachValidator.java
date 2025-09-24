package com.substring.irctc.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoachValidator implements ConstraintValidator<ValidCoach, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        System.out.println("validating coach numbers");
        return value > 100;
        //logic-- validation logic
    }
}
