package com.learning.banksystem.dto;

import com.learning.banksystem.model.CustomerType;

public class LegalCustomerDTO extends CustomerDTO {
    private String jobTitle;

    public LegalCustomerDTO(Integer id, String fullName, String number, String email) {
        super(id, fullName, number, email, CustomerType.LEGAL);
    }

    @Override
    public String toString() {
        return "LegalCustomer -> " +
                super.toString() +
                "Job Title=" + jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
