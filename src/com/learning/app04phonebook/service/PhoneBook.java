package com.learning.app04phonebook.service;

import com.learning.app04phonebook.model.Contact;
import com.learning.app04phonebook.model.PersonalContact;
import com.learning.app04phonebook.model.BusinessContact;

import java.util.*;

public class PhoneBook {
    private final HashMap<Integer, Contact> contacts = new HashMap<>();

    public void starter() {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 1;
        welcomeMessage();

        do {
            printMenu();
            try {
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
                        break;
                    default:
                        wrongInput();
                }
            } catch (Exception e) {
                wrongInput();
                scanner.nextLine();
            }
        } while(userChoice != 0);
        scanner.close();
    }

    private void welcomeMessage() {
        System.out.println("Welcome to Phone Book Application!");
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
        int userChoice = 0;

        do {
            addContactPrintMenu();
            try {
                userChoice = scanner.nextInt();
                scanner.nextLine();
                switch(userChoice) {
                    case 1:
                        addContactPersonalInputs(scanner);
                        break;
                    case 2:
                        addContactsBusinessInputs(scanner);
                        break;
                    case 0:
                        System.out.println("Adding Canceled!");
                        break;
                    default:
                        wrongInput();
                }
            } catch (Exception e) {
                wrongInput();
                scanner.nextLine();
            }
        } while(userChoice != 0 && userChoice != 1 && userChoice != 2);
    }

    private void addContactPrintMenu() {
        System.out.println("Please Choose Contact Type:");
        System.out.println("1. Personal");
        System.out.println("2. Business");
        System.out.println("0. Cancel Adding Contact");
    }

    private void addContactPersonalInputs(Scanner scanner) {
        System.out.print("Please Enter Contact Name: ");
        String name = scanner.nextLine();
        System.out.print("Please Enter Contact Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Please Enter Contact Number: ");
        String number = scanner.nextLine();
        PersonalContact personalContact = new PersonalContact(name, number);
        personalContact.setLastName(lastName);
        contacts.put(maxId() + 1, personalContact);
        System.out.println("New Contact Added Successfully!");
    }

    private void addContactsBusinessInputs(Scanner scanner) {
        System.out.print("Please Enter Contact Name: ");
        String name = scanner.nextLine();
        System.out.print("Please Enter Contact Number: ");
        String number = scanner.nextLine();
        System.out.print("Please Enter Contact Fax Number: ");
        String fax = scanner.nextLine();
        BusinessContact businessContact = new BusinessContact(name, number);
        businessContact.setFax(fax);
        contacts.put(maxId() + 1, businessContact);
        System.out.println("New Contact Added Successfully!");
    }

    private void printAllContacts() {
        if(contacts.isEmpty()) {
            System.out.println("Phone Book Is Empty!");
        } else {
            for(Map.Entry<Integer, Contact> contact : contacts.entrySet()) {
                System.out.println(contact);
            }
        }
    }

    private void deleteContact(Scanner scanner) {
        if(contacts.isEmpty()) {
            System.out.println("Phone Book Is Empty!");
        } else {
            int index = 1;

            while (index != 0) {
                printAllContacts();
                System.out.println("Enter the ID You Want to Delete: (or 0 to exit)");
                try {
                    index = scanner.nextInt();
                    scanner.nextLine();
                    if(index == 0) {
                        continue;
                    } else if(contacts.get(index) != null) {
                        contacts.remove(index);
                        System.out.println("Contact Deleted!");
                    } else {
                        wrongInput();
                    }
                } catch (Exception e) {
                    wrongInput();
                    scanner.nextLine();
                }
            }
        }
    }

    private int maxId() {
        if(contacts.isEmpty()) {
            return 0;
        } else {
            return Collections.max(contacts.keySet());
        }
    }

    private void wrongInput() {
        System.out.println("Please Enter a Valid Number!");
    }

}
