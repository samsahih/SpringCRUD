package com.crudexample.crud.validations.NotEmptyIfPresent;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class NotEmptyIfPresentValidator implements ConstraintValidator<INotEmptyIfPresentValidator, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || StringUtils.hasText(value);
    }
}
