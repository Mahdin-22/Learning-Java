package com.learning.banksystem.view.components;

import com.learning.banksystem.dto.CustomerDTO;
import com.learning.banksystem.model.CustomerType;
import com.learning.banksystem.service.exceptions.WrongInputException;
import com.learning.banksystem.util.ScannerWrapper;

import java.util.function.Function;


public abstract class AbstractCustomerUI {

    protected final ScannerWrapper scannerWrapper;

    public AbstractCustomerUI() {
        this.scannerWrapper = ScannerWrapper.getInstance();
    }

    public static AbstractCustomerUI fromCustomerType(CustomerType customerType) throws WrongInputException {
        return switch (customerType) {
            case REAL -> new RealCustomerUI();
            case LEGAL -> new LegalCustomerUI();
            default -> throw new WrongInputException();
        };
    }

    public CustomerDTO generateCustomer() {
        String fullName = scannerWrapper.getUserInput("Please Enter Customer Full Name:", Function.identity());
        String number = scannerWrapper.getUserInput("Please Enter Customer Number:", Function.identity());
        String email = scannerWrapper.getUserInput("Please Enter Customer E-mail:", Function.identity());
        return additionalGenerateCustomer(fullName, number, email);
    }

    protected abstract CustomerDTO additionalGenerateCustomer(String fullName, String number, String email);
    public abstract void editCustomer(CustomerDTO customer);
}
