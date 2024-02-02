package com.learning.banksystem.view;

import com.learning.banksystem.dto.CustomerDTO;
import com.learning.banksystem.facade.CustomerFacade;
import com.learning.banksystem.facade.impl.CustomerFacadeImpl;
import com.learning.banksystem.model.CustomerType;
import com.learning.banksystem.model.FileType;
import com.learning.banksystem.util.ScannerWrapper;
import com.learning.banksystem.view.components.AbstractCustomerUI;
import com.learning.banksystem.service.exceptions.*;

import java.util.List;
import java.util.function.Function;


public class ConsoleUI implements AutoCloseable {

    private final ScannerWrapper scannerWrapper;
    private final CustomerFacade customerFacade;

    public ConsoleUI() {
        scannerWrapper = ScannerWrapper.getInstance();
        customerFacade = CustomerFacadeImpl.getInstance();
    }

    public void startMenu() {

        customerFacade.initialData();
        Runtime.getRuntime().addShutdownHook(new Thread(this::saveOnExit));
        int userChoice;
        welcomeMessage();

        do {
            printMenu();
            userChoice = scannerWrapper.getUserInput("\nPlease Choose an Option:", Integer::valueOf);

            try {
                switch (userChoice) {
                    case 1 -> addCustomer();
                    case 2 -> printAllCustomers();
                    case 3 -> searchCustomers();
                    case 4 -> editCustomer();
                    case 5 -> deleteCustomer();
                    case 6 -> printAllDeletedCustomers();
                    case 7 -> saveData();
                    case 8 -> loadData();
                    case 9 -> addData();
                    case 0 -> System.out.println("See You Soon! ;)");
                    default -> wrongInput();
                }
            } catch (BaseException exception) {
                System.out.println(exception.getMessage());
            }
        } while(userChoice != 0);
    }

    private void saveOnExit() {
        customerFacade.saveOnExit();
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
        System.out.println("7. Save Data");
        System.out.println("8. Load Data");
        System.out.println("9. Add Data");
        System.out.println("0. Exit Application");
    }

    private void addCustomer() throws DuplicatedCustomerException, InvalidTypeException, WrongInputException, ValidationException {
        int userChoice;

        do {
            addCustomerPrintMenu();
            userChoice = scannerWrapper.getUserInput("Please Choose Customer Type:", Integer::valueOf);

            if(userChoice != 0) {
                customerFacade.addCustomer(AbstractCustomerUI
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
        List<CustomerDTO> allCustomers = customerFacade.getActiveCustomers();
        if(allCustomers.isEmpty()) {
            System.out.println("No Customers Yet!");
        } else {
            System.out.println("All Customers:");
            for (CustomerDTO customer : allCustomers) {
                System.out.println(customer);
            }
        }
    }

    private void searchCustomers() throws CustomerNotFoundException {
        String searchCase = scannerWrapper.getUserInput("Enter a Clause to Search: ", Function.identity());
        List<CustomerDTO> customers = customerFacade.searchCustomers(searchCase);
        customers.forEach(System.out::println);
    }

    private void editCustomer() throws CustomerNotFoundException, WrongInputException, ValidationException {
        Integer id = scannerWrapper.getUserInput("Enter the ID You Want to Edit: (or 0 to cancel editing)", Integer::valueOf);
        if (id != 0) {
            CustomerDTO customerDTO = customerFacade.getCustomer(id);
            System.out.println(customerDTO);
            AbstractCustomerUI
                    .fromCustomerType(customerDTO.getCustomerType())
                    .editCustomer(customerDTO);
            customerFacade.updateCustomer(customerDTO);
        } else {
            System.out.println("Editing Stopped!");
        }
    }

    private void deleteCustomer() throws CustomerNotFoundException {
        Integer id = scannerWrapper.getUserInput("Enter the ID You Want to Delete: (or 0 to cancel deleting)", Integer::valueOf);
        if(id != 0) {
            customerFacade.deleteCustomer(id);
            System.out.println("Customer Deleted!");
        } else {
            System.out.println("Deleting Stopped!");
        }
    }

    private void printAllDeletedCustomers() {
        List<CustomerDTO> allCustomers = customerFacade.getDeletedCustomers();
        if(allCustomers.isEmpty()) {
            System.out.println("No Deleted Customers Yet!");
        } else {
            System.out.println("Deleted Customers:");
            for (CustomerDTO customer : allCustomers) {
                System.out.println(customer);
            }
        }
    }

    private void saveData() throws FileException, InvalidTypeException {
        saveAndLoadDataPrintMenu("save");
        int userChoice = scannerWrapper.getUserInput("Please Choose Data Type:", Integer::valueOf);

        if(userChoice != 0) {
            FileType fileType = FileType.fromValue(userChoice);
            String fileName = scannerWrapper.getUserInput("Please Enter File Name:", Function.identity());
            customerFacade.saveData(fileName, fileType);
            System.out.println("All Data Saved Successfully!");
        } else {
            System.out.println("Saving Canceled!");
        }
    }

    private void loadData() throws FileException, InvalidTypeException {
        saveAndLoadDataPrintMenu("load");
        int userChoice = scannerWrapper.getUserInput("Please Choose Data Type:", Integer::valueOf);

        if(userChoice != 0) {
            FileType fileType = FileType.fromValue(userChoice);
            String fileName = scannerWrapper.getUserInput("Please Enter File Name:", Function.identity());
            customerFacade.loadData(fileName, fileType);
            System.out.println("All Data Loaded Successfully!");
        } else {
            System.out.println("Loading Canceled!");
        }
    }

    private void saveAndLoadDataPrintMenu(String action) {
        System.out.println("Data Types:");
        System.out.println("1. Serialized");
        System.out.println("2. JSON");
        System.out.println("0. Cancel " + (action.equals("save") ? "Saving " : "Loading ") + "Data");
    }

    private void addData() throws FileException {
        String fileName = scannerWrapper.getUserInput("Please Enter JSON File Name:", Function.identity());
        customerFacade.addData(fileName);
    }

    private void wrongInput() throws WrongInputException {
        throw new WrongInputException();
    }
    
    @Override
    public void close(){
        scannerWrapper.close();
    }
}
