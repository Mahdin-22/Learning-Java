package com.learning.app03_phoneBook;

import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}

class PhoneBook {
    private ArrayList<Contact> contacts;

    public PhoneBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(String name, String number) {
        Contact newContact = new Contact(name, number);
        contacts.add(newContact);
        System.out.println("New Entry Added!");
    }

    public void printContacts() {
        for(int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            System.out.println((i + 1) + ")\t" + contact.getName() + "\t==>\t" + contact.getNumber());
        }
    }

    public void deleteContact(int index) {
        if(index >= 0 && index < contacts.size()) {
            contacts.remove(index);
            System.out.println("Contact Deleted!");
        } else {
            System.out.println("Please Enter a Valid ID!");
        }
    }

    public boolean isEmpty() {
        return contacts.isEmpty();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        System.out.println("Wellcome to This Application!");
        System.out.println("-----------------------------");
        while(true) {
            System.out.println("\nPlease Choose an Option: \n");
            System.out.println("1. Enter New Entry");
            System.out.println("2. Print Phone Book");
            System.out.println("3. Delete an Entry");
            System.out.println("0. Exit");
            String userInput = scanner.nextLine();

            switch(userInput) {
                case "1":
                    System.out.println("Please Enter the Name:");
                    String name = scanner.nextLine();
                    System.out.println("Please Enter the Number:");
                    String number = scanner.nextLine();
                    phoneBook.addContact(name, number);
                    break;
                case "2":
                    if(phoneBook.isEmpty()) {
                        System.out.println("Phone Book Is Empty!");
                    } else {
                        phoneBook.printContacts();
                    }
                    break;
                case "3":
                    if(phoneBook.isEmpty()) {
                        System.out.println("Phone Book Is Empty!");
                    } else {
                        phoneBook.printContacts();
                        System.out.println("Enter the ID You Want to Delete:");
                        while(true) {
                            if(scanner.hasNextInt()) {
                                phoneBook.deleteContact(scanner.nextInt() - 1);
                                break;
                            } else {
                                System.out.println("Please Enter a Number!");
                                scanner.next();
                            }
                        }
                    }
                    break;
                case "0":
                    System.out.println("See You Soon! ;)");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Enter a Valid Number!");
                    break;
            }
        }
    }
}
