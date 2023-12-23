package com.learning.app04phonebook.model;

public abstract class Contact {
    protected String name;
    protected String number;
    protected ContactType type;

    public Contact(String name, String number, ContactType type) {
        this.name = capitalizeFirstLetter(name);
        this.number = number;
        this.type = type;
    }

    protected String capitalizeFirstLetter(String str) {
        if(str != null && !str.isEmpty()) {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
        return str;
    }

    /*public ContactType getType() {
        return type;
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
    }*/
}
