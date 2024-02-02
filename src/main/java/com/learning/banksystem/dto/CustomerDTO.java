package com.learning.banksystem.dto;

import com.learning.banksystem.model.CustomerType;

public class CustomerDTO {
    private Integer id;
    private String fullName;
    private String number;
    private String email;
    private final CustomerType customerType;

    public CustomerDTO(Integer id, String fullName, String number, String email, CustomerType customerType) {
        this.id = id;
        this.fullName = fullName;
        this.number = number;
        this.email = email;
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        return "ID=" + id +
                ", Full Name='" + fullName + '\'' +
                ", Number='" + number + '\'' +
                ", E-mail='" + email + '\'' +
                ", ";
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public CustomerType getCustomerType() {
        return customerType;
    }
}
