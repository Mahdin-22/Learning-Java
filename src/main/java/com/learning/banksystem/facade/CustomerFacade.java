package com.learning.banksystem.facade;

import com.learning.banksystem.dto.CustomerDTO;
import com.learning.banksystem.model.FileType;
import com.learning.banksystem.service.exceptions.CustomerNotFoundException;
import com.learning.banksystem.service.exceptions.DuplicatedCustomerException;
import com.learning.banksystem.service.exceptions.FileException;
import com.learning.banksystem.service.exceptions.ValidationException;

import java.util.List;

public interface CustomerFacade {
    void addCustomer(CustomerDTO customer) throws DuplicatedCustomerException, ValidationException;
    void updateCustomer(CustomerDTO customer) throws ValidationException, CustomerNotFoundException;
    List<CustomerDTO> getActiveCustomers();
    List<CustomerDTO> searchCustomers(String name) throws CustomerNotFoundException;
    CustomerDTO getCustomer(Integer id) throws CustomerNotFoundException;
    void deleteCustomer(Integer id) throws CustomerNotFoundException;
    List<CustomerDTO> getDeletedCustomers();
    void saveData(String fileName, FileType fileType) throws FileException;
    void loadData(String fileName, FileType fileType) throws FileException;
    void initialData();
    void saveOnExit();
    void addData(String fileName) throws FileException;
}
