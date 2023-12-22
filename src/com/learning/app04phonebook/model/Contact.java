package com.learning.app04phonebook.model;

public abstract class Contact {
    private String name;
    private String number;
    private final ContactType type;

    public Contact(String name, String number, ContactType type) {
        this.name = name;
        this.number = number;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", type=" + type +
                '}';
    }

    public ContactType getType() {
        return type;
    }

    private String capitalizeFirstLetter(String str) {
        if(str != null && !str.isEmpty()) {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
        return str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = capitalizeFirstLetter(name);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
