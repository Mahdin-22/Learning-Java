package com.learning.banksystem.service.exceptions;

public class CustomerNotFoundException extends BaseException {
    public CustomerNotFoundException() {
        super("Customer Not Found!");
    }
}
