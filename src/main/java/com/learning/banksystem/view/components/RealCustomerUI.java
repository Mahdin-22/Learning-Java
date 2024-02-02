package com.learning.banksystem.view.components;

import com.learning.banksystem.dto.CustomerDTO;
import com.learning.banksystem.dto.RealCustomerDTO;

import java.util.function.Function;


public class RealCustomerUI extends AbstractCustomerUI {

    public RealCustomerUI() {
        super();
    }

    @Override
    public CustomerDTO additionalGenerateCustomer(String fullName, String number, String email) {
        String birthDate = scannerWrapper.getUserInput("Please Enter Customer Birthday Date:", Function.identity());
        RealCustomerDTO realCustomer = new RealCustomerDTO(null, fullName, number, email);
        realCustomer.setBirthDate(birthDate);
        return realCustomer;
    }

    @Override
    public void editCustomer(CustomerDTO customer) {
        RealCustomerDTO realCustomer = (RealCustomerDTO) customer;
        String fullName = scannerWrapper.getUserInput("Please Enter Customer Full Name:", Function.identity());
        String number = scannerWrapper.getUserInput("Please Enter Customer Number:", Function.identity());
        String email = scannerWrapper.getUserInput("Please Enter Customer E-mail:", Function.identity());
        String birthDate = scannerWrapper.getUserInput("Please Enter Customer Birthday Date:", Function.identity());
        customer.setFullName(fullName);
        customer.setNumber(number);
        customer.setEmail(email);
        realCustomer.setBirthDate(birthDate);
    }
}
