package com.crudexample.crud.validations.NotEmptyIfPresent;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotEmptyIfPresentValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface INotEmptyIfPresentValidator {
    String message() default "Field cannot be empty if present";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}