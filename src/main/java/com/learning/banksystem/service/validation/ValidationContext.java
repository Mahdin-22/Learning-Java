package com.learning.banksystem.service.validation;

import com.learning.banksystem.service.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class ValidationContext<T> {
    private List<Validation<T>> validations;

    public ValidationContext () {
        this.validations = new ArrayList<>();
    }

    public void addValidation (Validation<T> validation) {
        validations.add(validation);
    }

    public void validate(T object) throws ValidationException {
        for (Validation<T> validation : validations) {
            validation.validate(object);
        }
    }
}
