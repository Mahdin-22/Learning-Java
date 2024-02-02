package com.learning.banksystem.service.exceptions;

public class WrongInputException extends BaseException {
    public WrongInputException() {
        super("Choose a Valid Option!");
    }
}
