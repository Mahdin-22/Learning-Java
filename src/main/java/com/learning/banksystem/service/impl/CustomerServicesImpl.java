package com.learning.banksystem.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.banksystem.model.Customer;
import com.learning.banksystem.model.FileType;
import com.learning.banksystem.service.CustomerServices;
import com.learning.banksystem.service.exceptions.CustomerNotFoundException;
import com.learning.banksystem.service.exceptions.DuplicatedCustomerException;
import com.learning.banksystem.service.exceptions.FileException;
import com.learning.banksystem.service.exceptions.ValidationException;
import com.learning.banksystem.util.MapperWrapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CustomerServicesImpl implements CustomerServices {

    private static final CustomerServicesImpl INSTANCE;
    public static CustomerServicesImpl getInstance() {
        return INSTANCE;
    }
    static {
        INSTANCE = new CustomerServicesImpl();
    }

    public CustomerServicesImpl() {}

    private ArrayList<Customer> customers = new ArrayList<>();
    private final ObjectMapper objectMapper = MapperWrapper.getInstance();

    @Override
    public void addCustomer(Customer customer) throws DuplicatedCustomerException, ValidationException {
        Optional<Customer> foundCustomer = customers.stream()
                .filter(it -> it.equals(customer))
                .findFirst();

        if (foundCustomer.isPresent())
            throw new DuplicatedCustomerException();

        customers.add(customer);
    }

    @Override
    public List<Customer> getActiveCustomers() {
        return customers.stream()
                .filter(customer -> !customer.getDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> searchCustomers(String searchCase) throws CustomerNotFoundException {
        List<Customer> foundCustomers = customers.stream()
                .filter(customer -> !customer.getDeleted())
                .filter(customer -> customer.getFullName().toLowerCase().contains(searchCase.toLowerCase())
                        || customer.getNumber().toLowerCase().contains(searchCase.toLowerCase())
                        || customer.getEmail().toLowerCase().contains(searchCase.toLowerCase()))
                .collect(Collectors.toList());
        if (foundCustomers.isEmpty())
            throw new CustomerNotFoundException();
        else
            return foundCustomers;
        /*But how to search if birthDate & jobTitle contains searchCase???*/
    }

    @Override
    public Customer getCustomer(Integer id) throws CustomerNotFoundException {
        return customers.stream()
                .filter(customer -> !customer.getDeleted())
                .filter(customer -> customer.getId().equals(id))
                .findFirst().orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public void deleteCustomer(Integer id) throws CustomerNotFoundException {
        getCustomer(id).setDeleted(true);
    }

    @Override
    public List<Customer> getDeletedCustomers() {
        return customers.stream()
                .filter(Customer::getDeleted)
                .collect(Collectors.toList());
    }

    @Override
    public void saveData(String fileName, FileType fileType) throws FileException {
        switch (fileType) {
            case FileType.SERILIZED -> saveSerialized(fileName);
            case FileType.JSON -> saveJSON(fileName);
        }
    }

    private void saveSerialized(String fileName) throws FileException {
        try {
            File file = new File(fileName + ".crm");
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(customers);
            }
        } catch (IOException e) {
            throw new FileException();
        }
    }

    private void saveJSON(String fileName) throws FileException {
        try {
            File file = new File(fileName + ".jsn");
            if (!file.exists()) {
                file.createNewFile();
            }
            objectMapper.writeValue(file, customers);
        } catch (IOException e) {
            throw new FileException();
        }
    }

    @Override
    public void loadData(String fileName, FileType fileType) throws FileException {
        switch (fileType) {
            case FileType.SERILIZED -> loadSerialized(fileName);
            case FileType.JSON -> loadJSON(fileName);
        }
    }

    private void loadSerialized(String fileName) throws FileException {
        try {
            try (FileInputStream fileInputStream = new FileInputStream(fileName + ".crm");
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                customers = (ArrayList<Customer>) objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new FileException();
        }
    }

    private void loadJSON(String fileName) throws FileException {
        try {
            customers = objectMapper.readValue(new File(fileName + ".jsn"),
                    new TypeReference<ArrayList<Customer>>() {});
        } catch (IOException e) {
            throw new FileException();
        }
    }

    @Override
    public void initialData() {
        try {
            loadJSON("InitialData");
        } catch (FileException ignored) {}
    }

    @Override
    public void saveOnExit() {
        try {
            saveJSON("InitialData");
        } catch (FileException ignored) {}
    }

    @Override
    public void addData(String fileName) throws FileException {
        try {
            ArrayList<Customer> newCustomers = objectMapper.readValue(new File(fileName + ".jsn"),
                    new TypeReference<ArrayList<Customer>>() {});
            customers.addAll(newCustomers);
        } catch (IOException e) {
            throw new FileException();
        }
    }
}
