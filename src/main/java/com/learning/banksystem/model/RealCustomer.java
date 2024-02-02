package com.learning.banksystem.model;


import java.io.Serializable;

public class RealCustomer extends Customer implements Serializable {

    private String birthDate;

    public RealCustomer() {
        super(CustomerType.REAL);
    }

    public RealCustomer(String fullName, String number, String email) {
        super(fullName, number, email, CustomerType.REAL);
    }

    @Override
    public String toString() {
        return "RealCustomer -> " +
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
