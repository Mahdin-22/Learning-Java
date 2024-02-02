package com.learning.banksystem.model;


import com.learning.banksystem.service.exceptions.InvalidTypeException;

public enum CustomerType {
    REAL(1),
    LEGAL(2);

    private final int value;

    CustomerType(int value) {
        this.value = value;
    }

    public static CustomerType fromValue(int value) throws InvalidTypeException {
        for(CustomerType type:values()) {
            if(type.value == value) {
                return type;
            }
        }
        throw new InvalidTypeException();
    }
}
