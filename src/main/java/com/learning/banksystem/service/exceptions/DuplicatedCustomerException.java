package com.learning.banksystem.service.exceptions;

public class DuplicatedCustomerException extends BaseException {
    public DuplicatedCustomerException() {
        super("Duplicated Customer!");
    }
}
