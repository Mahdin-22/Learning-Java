package com.learning.app05customers.service.impl;

import com.learning.app05customers.model.Customer;
import com.learning.app05customers.service.CustomerServices;
import com.learning.app05customers.service.exceptions.CustomerNotFoundException;
import com.learning.app05customers.service.exceptions.DuplicatedCustomerException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CustomerServicesImpl implements CustomerServices {

    private static final CustomerServicesImpl INSTANCE;
    public static CustomerServicesImpl getInstance() {
        return INSTANCE;
    }
    static {
        INSTANCE = new CustomerServicesImpl();
    }

    public CustomerServicesImpl() {

    }

    private final ArrayList<Customer> customers = new ArrayList<>();

    @Override
    public void addCustomer(Customer customer) throws DuplicatedCustomerException {
        customers.stream()
                .filter(it -> it.equals(customer))
                .findAny().orElseThrow(DuplicatedCustomerException::new);
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
                .filter(customer -> customer.getFullName().contains(searchCase)
                        || customer.getNumber().contains(searchCase)
                        || customer.getEmail().contains(searchCase))
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
}
