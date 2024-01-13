package com.learning.app05customers.view.components;

import com.learning.app05customers.model.Customer;
import com.learning.app05customers.model.RealCustomer;

import java.util.function.Function;


public class RealCustomerUI extends AbstractCustomerUI {

    public RealCustomerUI() {
        super();
    }

    @Override
    public Customer additionalGenerateCustomer(String fullName, String number, String email) {
        String birthDate = scannerWrapper.getUserInput("Please Enter Customer Birthday Date:", Function.identity());
        RealCustomer realCustomer = new RealCustomer(fullName, number, email);
        realCustomer.setBirthDate(birthDate);
        return realCustomer;
    }

    @Override
    public void editCustomer(Customer customer) {
        RealCustomer realCustomer = (RealCustomer) customer;
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
