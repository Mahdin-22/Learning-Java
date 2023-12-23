package com.learning.app04phonebook.model;

public class BusinessContact extends Contact {

    private String fax;

    public BusinessContact(String name, String number) {
        super(name, number, ContactType.BUSINESS);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + super.name + '\'' +
                ", number='" + super.number + '\'' +
                ", fax number='" + fax + '\'' +
                ", type=" + super.type +
                '}';
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
