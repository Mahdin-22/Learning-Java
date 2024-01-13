package com.learning.app05customers.model;


public class RealCustomer extends Customer {

    private String birthDate;

    public RealCustomer(String fullName, String number, String email) {
        super(fullName, number, email, CustomerType.REAL);
    }

    @Override
    public String toString() {
        return "RealCustomer ->" +
                super.toString() +
                "Birthday Date=" + birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
