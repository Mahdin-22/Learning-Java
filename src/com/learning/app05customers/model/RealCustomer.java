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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RealCustomer
                && ((RealCustomer) obj).getFullName().equals(getFullName());
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
