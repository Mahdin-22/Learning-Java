package com.learning.app05customers.view.components;

import com.learning.app05customers.model.Customer;
import com.learning.app05customers.model.CustomerType;
import com.learning.app05customers.service.exceptions.WrongInputException;
import com.learning.app05customers.util.ScannerWrapper;

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

    public Customer generateCustomer() {
        String fullName = scannerWrapper.getUserInput("Please Enter Customer Full Name:", Function.identity());
        String number = scannerWrapper.getUserInput("Please Enter Customer Number:", Function.identity());
        String email = scannerWrapper.getUserInput("Please Enter Customer E-mail:", Function.identity());
        return additionalGenerateCustomer(fullName, number, email);
    }

    protected abstract Customer additionalGenerateCustomer(String fullName, String number, String email);
    public abstract void editCustomer(Customer customer);
}
