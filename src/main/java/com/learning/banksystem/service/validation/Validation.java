package com.learning.banksystem.service.validation;

import com.learning.banksystem.service.exceptions.ValidationException;

@FunctionalInterface
public interface Validation <T> {
    void validate (T t) throws ValidationException;
}
