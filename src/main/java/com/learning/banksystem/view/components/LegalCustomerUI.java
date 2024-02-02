package com.learning.banksystem.view.components;

import com.learning.banksystem.dto.CustomerDTO;
import com.learning.banksystem.dto.LegalCustomerDTO;

import java.util.function.Function;


public class LegalCustomerUI extends AbstractCustomerUI {

    public LegalCustomerUI() {
        super();
    }

    @Override
    public CustomerDTO additionalGenerateCustomer(String fullName, String number, String email) {
        String jobTitle = scannerWrapper.getUserInput("Please Enter Customer Job Title:", Function.identity());
        LegalCustomerDTO legalCustomer = new LegalCustomerDTO(null, fullName, number, email);
        legalCustomer.setJobTitle(jobTitle);
        return legalCustomer;
    }

    @Override
    public void editCustomer(CustomerDTO customer) {
        LegalCustomerDTO legalCustomer = (LegalCustomerDTO) customer;
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
