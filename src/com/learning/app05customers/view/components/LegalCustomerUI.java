package com.learning.app05customers.view.components;

import com.learning.app05customers.model.Customer;
import com.learning.app05customers.model.LegalCustomer;

import java.util.function.Function;


public class LegalCustomerUI extends AbstractCustomerUI {

    public LegalCustomerUI() {
        super();
    }

    @Override
    public Customer additionalGenerateCustomer(String fullName, String number, String email) {
        String jobTitle = scannerWrapper.getUserInput("Please Enter Customer Job Title:", Function.identity());
        LegalCustomer legalCustomer = new LegalCustomer(fullName, number, email);
        legalCustomer.setJobTitle(jobTitle);
        return legalCustomer;
    }

    @Override
    public void editCustomer(Customer customer) {
        LegalCustomer legalCustomer = (LegalCustomer) customer;
        String fullName = scannerWrapper.getUserInput("Please Enter Customer Full Name:", Function.identity());
        String number = scannerWrapper.getUserInput("Please Enter Customer Number:", Function.identity());
        String email = scannerWrapper.getUserInput("Please Enter Customer E-mail:", Function.identity());
        String jobTitle = scannerWrapper.getUserInput("Please Enter Customer Job Title:", Function.identity());
        customer.setFullName(fullName);
        customer.setNumber(number);
        customer.setEmail(email);
        legalCustomer.setJobTitle(jobTitle);
    }
}
