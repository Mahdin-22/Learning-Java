package com.learning.app04phonebook.model;

public class PersonalContact extends Contact {

    private String lastName;

    public PersonalContact(String name, String number) {
        super(name, number, ContactType.PERSONAL);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + super.name + '\'' +
                ", last name='" + lastName + '\'' +
                ", number='" + super.number + '\'' +
                ", type=" + super.type +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = capitalizeFirstLetter(lastName);
    }
}
