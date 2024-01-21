package com.learning.app05customers.model;


public class LegalCustomer extends Customer {

    private String jobTitle;

    public LegalCustomer(String fullName, String number, String email) {
        super(fullName, number, email, CustomerType.LEGAL);
    }

    @Override
    public String toString() {
        return "LegalCustomer ->" +
                super.toString() +
                "Job Title=" + jobTitle;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LegalCustomer
                && ((LegalCustomer) obj).getFullName().equals(getFullName());
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
