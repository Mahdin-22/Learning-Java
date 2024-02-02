package com.learning.banksystem.facade.impl;

import com.learning.banksystem.dto.CustomerDTO;
import com.learning.banksystem.service.exceptions.ValidationException;
import com.learning.banksystem.service.validation.ValidationContext;
import com.learning.banksystem.util.NumberValidator;

public class CustomerValidationContext extends ValidationContext<CustomerDTO> {
    public CustomerValidationContext() {

        // Name Validation
        addValidation(customer ->  {
            String name = customer.getFullName();
            if (name == null || name.trim().isEmpty()) {
                throw new ValidationException("No Null or Empty Name Allowed!");
            }
        });

        // Number Validation
        addValidation(customer ->  {
            String number = customer.getNumber();
            if (!NumberValidator.validate(number)) {
                throw new ValidationException("Invalid Number Format!");
            }
        });
    }
}
