package com.learning.app04phonebook.service;

import com.learning.app04phonebook.model.BusinessContact;
import com.learning.app04phonebook.model.Contact;
import com.learning.app04phonebook.model.PersonalContact;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void starter() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        welcomeMessage();

        do {
            printMenu();
            if(scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine();

                switch(userChoice) {
                    case 1:
                        addContact(scanner);
                        break;
                    case 2:
                        printAllContacts();
                        break;
                    case 3:
                        deleteContact(scanner);
                        break;
                    case 0:
                        System.out.println("See You Soon! ;)");
                        userChoice = 0;
                        break;
                    default:
                        System.out.println("Please Enter a Valid Number!");
                        break;
                }
                break;
            } else {
                System.out.println("Please Enter a Number!");
                scanner.nextLine();
            }
        } while(userChoice != 0);
        scanner.close();
    }

    private void welcomeMessage() {
        System.out.println("Wellcome to Phone Book Application!");
        System.out.println("-----------------------------------");
    }

    private void printMenu() {
        System.out.println("\nPlease Choose an Option: \n");
        System.out.println("1. Enter New Contact");
        System.out.println("2. Print All Contacts");
        System.out.println("3. Delete a Contact");
        System.out.println("0. Exit Application");
    }

    private void addContact(Scanner scanner) {
        System.out.println("Please Choose Contact Type:");
        System.out.println("1. Personal");
        System.out.println("2. Business");
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        if(userChoice == 1) {
            System.out.print("Please Enter Contact Name: ");
            String name = scanner.nextLine();
            System.out.print("Please Enter Contact Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Please Enter Contact Number: ");
            String number = scanner.nextLine();
            PersonalContact personalContact = new PersonalContact(name, number);
            personalContact.setLastName(lastName);
            contacts.add(personalContact);
        } else {
            System.out.print("Please Enter Contact Name: ");
            String name = scanner.nextLine();
            System.out.print("Please Enter Contact Number: ");
            String number = scanner.nextLine();
            System.out.print("Please Enter Contact Fax Number: ");
            String fax = scanner.nextLine();
            BusinessContact businessContact = new BusinessContact(name, number);
            businessContact.setFax(fax);
            contacts.add(businessContact);
        }

        System.out.println("New Contact Added Successfully!");
    }

    private void printAllContacts() {
        if(contacts.isEmpty()) {
            System.out.println("Phone Book Is Empty!");
        } else {
            for(Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    private void deleteContact(Scanner scanner) {
        if(phoneBook.isEmpty()) {
            System.out.println("Phone Book Is Empty!");
        } else {
            phoneBook.printContacts();
            System.out.println("Enter the ID You Want to Delete:");
            while (true) {
                if (scanner.hasNextInt()) {
                    if(index >= 0 && index < contacts.size()) {
                        contacts.remove(index);
                        System.out.println("Contact Deleted!");
                    } else {
                        System.out.println("Please Enter a Valid ID!");
                    }
                    phoneBook.deleteContact(scanner.nextInt() - 1);
                    break;
                } else {
                    System.out.println("Please Enter a Number!");
                    scanner.nextLine();
                }
            }
        }
    }

}
