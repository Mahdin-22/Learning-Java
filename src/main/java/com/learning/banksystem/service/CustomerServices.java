package com.learning.banksystem.service;

import com.learning.banksystem.model.Customer;
import com.learning.banksystem.model.FileType;
import com.learning.banksystem.service.exceptions.CustomerNotFoundException;
import com.learning.banksystem.service.exceptions.DuplicatedCustomerException;
import com.learning.banksystem.service.exceptions.FileException;
import com.learning.banksystem.service.exceptions.ValidationException;

import java.util.List;


public interface CustomerServices {
    void addCustomer(Customer customer) throws DuplicatedCustomerException, ValidationException;
    List<Customer> getActiveCustomers();
    List<Customer> searchCustomers(String name) throws CustomerNotFoundException;
    Customer getCustomer(Integer id) throws CustomerNotFoundException;
    void deleteCustomer(Integer id) throws CustomerNotFoundException;
    List<Customer> getDeletedCustomers();
    void saveData(String fileName, FileType fileType) throws FileException;
    void loadData(String fileName, FileType fileType) throws FileException;
    void initialData();
    void saveOnExit();
    void addData(String fileName) throws FileException;
}
