package com.learning.app04phonebook.model;

public class PersonalContact extends Contact {

    private String lastName;

    public PersonalContact(String name, String number) {
        super(name, number, ContactType.PERSONAL);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
