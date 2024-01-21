package com.learning.app05customers.service;

import com.learning.app05customers.model.Customer;
import com.learning.app05customers.service.exceptions.CustomerNotFoundException;
import com.learning.app05customers.service.exceptions.DuplicatedCustomerException;

import java.util.List;


public interface CustomerServices {

    void addCustomer(Customer customer) throws DuplicatedCustomerException;
    List<Customer> getActiveCustomers();
    List<Customer> searchCustomers(String name) throws CustomerNotFoundException;
    Customer getCustomer(Integer id) throws CustomerNotFoundException;
    void deleteCustomer(Integer id) throws CustomerNotFoundException;
    List<Customer> getDeletedCustomers();
}
