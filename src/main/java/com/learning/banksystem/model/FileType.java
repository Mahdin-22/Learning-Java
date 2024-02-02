package com.learning.banksystem.model;


import com.learning.banksystem.service.exceptions.InvalidTypeException;

public enum FileType {
    SERILIZED(1),
    JSON(2);

    private final int value;

    FileType(int value) {
        this.value = value;
    }

    public static FileType fromValue(int value) throws InvalidTypeException {
        for(FileType type:values()) {
            if(type.value == value) {
                return type;
            }
        }
        throw new InvalidTypeException();
    }
}
