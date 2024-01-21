package com.learning.app05customers.model;


import com.learning.app05customers.service.exceptions.InvalidCustomerTypeException;

public enum CustomerType {
    REAL(1),
    LEGAL(2);

    private final int value;

    CustomerType(int value) {
        this.value = value;
    }

    public static CustomerType fromValue(int value) throws InvalidCustomerTypeException {
        for(CustomerType type:values()) {
            if(type.value == value) {
                return type;
            }
        }
        throw new InvalidCustomerTypeException();
    }
}
