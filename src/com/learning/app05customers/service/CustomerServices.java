package com.learning.app05customers.service;

import com.learning.app05customers.model.Customer;

import java.util.List;


public interface CustomerServices {

    void addCustomer(Customer customer);
    List<Customer> getActiveCustomers();
    List<Customer> searchCustomers(String name);
    Customer getCustomer(Integer id);
    void deleteCustomer(Integer id);
    List<Customer> getDeletedCustomers();
}
