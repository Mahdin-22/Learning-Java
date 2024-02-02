package com.learning.banksystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "customerType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LegalCustomer.class, name = "LEGAL"),
        @JsonSubTypes.Type(value = RealCustomer.class, name = "REAL"),
})

public abstract class Customer implements Serializable {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(1);
    @JsonIgnore
    private final Integer id;
    private String fullName;
    private String number;
    private String email;
    private final CustomerType customerType;
    private Boolean deleted;

    public Customer(CustomerType customerType) {
        this.id = ID_COUNTER.getAndIncrement();
        this.customerType = customerType;
    }
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
