package com.learning.app05customers.service.exceptions;

public class WrongInputException extends BaseException {
    public WrongInputException() {
        super("Choose a Valid Option!");
    }
}
