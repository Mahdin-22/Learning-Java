package com.learning.app05customers.model;

import java.util.concurrent.atomic.AtomicInteger;


public abstract class Customer {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(1);
    private final Integer id;
    private String fullName;
    private String number;
    private String email;
    private final CustomerType customerType;
    private Boolean deleted;

    public Customer(String fullName, String number, String email, CustomerType customerType) {
        this.id = ID_COUNTER.getAndIncrement();
        this.fullName = capitalizeFirstLetter(fullName);
        this.number = number;
        this.email = email;
        this.customerType = customerType;
        this.deleted = false;

    }

    @Override
    public String toString() {
        return "ID=" + id +
                ", Full Name='" + fullName + '\'' +
                ", Number='" + number + '\'' +
                ", E-mail='" + email + '\'' +
                ", ";
    }

    private String capitalizeFirstLetter(String str) {
        if(str != null && !str.isEmpty()) {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
        }
        return str;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = capitalizeFirstLetter(fullName);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }
}
