package com.example.ProiectMagazinSuplimenteAlimentare.validators;

import com.example.ProiectMagazinSuplimenteAlimentare.constants.RoleType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class RoleSubsetValidator implements ConstraintValidator<RoleSubset, String> {
    private RoleType[] subset;

    @Override
    public void initialize(RoleSubset constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(subset)
                .anyMatch(roleType -> roleType.name().equals(value));
    }
}