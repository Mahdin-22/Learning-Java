package com.learning.app05customers.service.exceptions;

public class CustomerNotFoundException extends BaseException {
    public CustomerNotFoundException() {
        super("Customer Not Found!");
    }
}
