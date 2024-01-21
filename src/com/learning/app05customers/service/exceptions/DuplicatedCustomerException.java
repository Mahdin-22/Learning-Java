package com.learning.app05customers.service.exceptions;

public class DuplicatedCustomerException extends BaseException {
    public DuplicatedCustomerException() {
        super("Duplicated Customer!");
    }
}
