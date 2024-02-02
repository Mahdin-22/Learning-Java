package com.learning.banksystem.dto;

import com.learning.banksystem.model.CustomerType;

public class RealCustomerDTO extends CustomerDTO {
    private String birthDate;

    public RealCustomerDTO(Integer id, String fullName, String number, String email) {
        super(id, fullName, number, email, CustomerType.REAL);
    }

    @Override
    public String toString() {
        return "RealCustomer -> " +
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
