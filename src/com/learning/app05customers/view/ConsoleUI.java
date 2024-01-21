package com.learning.app05customers.view;

import com.learning.app05customers.model.Customer;
import com.learning.app05customers.model.CustomerType;
import com.learning.app05customers.service.CustomerServices;
import com.learning.app05customers.service.exceptions.*;
import com.learning.app05customers.service.impl.CustomerServicesImpl;
import com.learning.app05customers.util.ScannerWrapper;
import com.learning.app05customers.view.components.AbstractCustomerUI;

import java.util.List;
import java.util.function.Function;


public class ConsoleUI implements AutoCloseable {

    private final ScannerWrapper scannerWrapper;
    private final CustomerServices customerServices;

    public ConsoleUI() {
        scannerWrapper = ScannerWrapper.getInstance();
        customerServices = CustomerServicesImpl.getInstance();
    }

    public void startMenu() {
        int userChoice;
        welcomeMessage();

        do {
            printMenu();
            userChoice = scannerWrapper.getUserInput("Please Choose an Option:", Integer::valueOf);

            try {
                switch (userChoice) {
                    case 1 -> addCustomer();
                    case 2 -> printAllCustomers();
                    case 3 -> searchCustomers();
                    case 4 -> editCustomer();
                    case 5 -> deleteCustomer();
                    case 6 -> printAllDeletedCustomers();
                    case 0 -> System.out.println("See You Soon! ;)");
                    default -> wrongInput();
                }
            } catch (BaseException exception) {
                System.out.println(exception.getMessage());
            }
        } while(userChoice != 0);
    }

    private void welcomeMessage() {
        System.out.println("Welcome to Customers Application!");
        System.out.println("-----------------------------------");
    }

    private void printMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add New Customer");
        System.out.println("2. Print All Customers");
        System.out.println("3. Search Customers");
        System.out.println("4. Edit Customer");
        System.out.println("5. Delete Customer");
        System.out.println("6. Print Deleted Customers");
        System.out.println("0. Exit Application");
    }

    private void addCustomer() throws DuplicatedCustomerException, InvalidCustomerTypeException, WrongInputException {
        int userChoice;

        do {
            addCustomerPrintMenu();
            userChoice = scannerWrapper.getUserInput("Please Choose Customer Type:", Integer::valueOf);

            if(userChoice != 0) {
                customerServices.addCustomer(AbstractCustomerUI
                        .fromCustomerType(CustomerType.fromValue(userChoice))
                        .generateCustomer());
                System.out.println("New Customer Added Successfully!");
            } else {
                System.out.println("Adding Stopped!");
            }
        } while(userChoice != 0);
    }

    private void addCustomerPrintMenu() {
        System.out.println("Customer Types:");
        System.out.println("1. Real");
        System.out.println("2. Legal");
        System.out.println("0. Stop Adding Customers");
    }

    private void printAllCustomers() {
        List<Customer> allCustomers = customerServices.getActiveCustomers();
        if(allCustomers.isEmpty()) {
            System.out.println("No Customers Yet!");
        } else {
            System.out.println("All Customers:");
            for (Customer customer : allCustomers) {
                System.out.println(customer);
            }
        }
    }

    private void searchCustomers() throws CustomerNotFoundException {
        String searchCase = scannerWrapper.getUserInput("Enter a Clause to Search: ", Function.identity());
        List<Customer> customers = customerServices.searchCustomers(searchCase);
        customers.forEach(System.out::println);
    }

    private void editCustomer() throws CustomerNotFoundException, WrongInputException {
        Integer id = scannerWrapper.getUserInput("Enter the ID You Want to Edit: (or 0 to cancel editing)", Integer::valueOf);
        if (id != 0) {
            Customer customer = customerServices.getCustomer(id);
            System.out.println(customer);
            AbstractCustomerUI
                    .fromCustomerType(customer.getCustomerType())
                    .editCustomer(customer);
        } else {
            System.out.println("Editing Stopped!");
        }
    }

    private void deleteCustomer() throws CustomerNotFoundException {
        Integer id = scannerWrapper.getUserInput("Enter the ID You Want to Delete: (or 0 to cancel deleting)", Integer::valueOf);
        if(id != 0) {
            customerServices.deleteCustomer(id);
            System.out.println("Customer Deleted!");
        } else {
            System.out.println("Deleting Stopped!");
        }
    }

    private void printAllDeletedCustomers() {
        List<Customer> allCustomers = customerServices.getDeletedCustomers();
        if(allCustomers.isEmpty()) {
            System.out.println("No Deleted Customers Yet!");
        } else {
            System.out.println("Deleted Customers:");
            for (Customer customer : allCustomers) {
                System.out.println(customer);
            }
        }
    }

    private void wrongInput() throws WrongInputException {
        throw new WrongInputException();
    }
    
    @Override
    public void close(){
        scannerWrapper.close();
    }
}
