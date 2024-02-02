package com.learning.banksystem.facade.impl;

import com.learning.banksystem.dto.CustomerDTO;
import com.learning.banksystem.facade.CustomerFacade;
import com.learning.banksystem.mapper.CustomerMapstruct;
import com.learning.banksystem.model.Customer;
import com.learning.banksystem.model.FileType;
import com.learning.banksystem.service.CustomerServices;
import com.learning.banksystem.service.exceptions.CustomerNotFoundException;
import com.learning.banksystem.service.exceptions.DuplicatedCustomerException;
import com.learning.banksystem.service.exceptions.FileException;
import com.learning.banksystem.service.exceptions.ValidationException;
import com.learning.banksystem.service.impl.CustomerServicesImpl;
import com.learning.banksystem.service.validation.ValidationContext;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {

    private final CustomerServices customerServices;
    private final CustomerMapstruct customerMapstruct;
    private ValidationContext<CustomerDTO> validationContext;

    private static final CustomerFacadeImpl INSTANCE;
    public static CustomerFacadeImpl getInstance() {
        return INSTANCE;
    }
    static {
        INSTANCE = new CustomerFacadeImpl();
    }

    private CustomerFacadeImpl() {
        this.customerServices = CustomerServicesImpl.getInstance();
        this.customerMapstruct = Mappers.getMapper(CustomerMapstruct.class);
        this.validationContext = new CustomerValidationContext();
    }

    @Override
    public void addCustomer(CustomerDTO customer) throws DuplicatedCustomerException, ValidationException {
        validationContext.validate(customer);
        customerServices.addCustomer(customerMapstruct.mapToCustomer(customer));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) throws ValidationException, CustomerNotFoundException {
        validationContext.validate(customerDTO);
        Customer customer = customerServices.getCustomer(customerDTO.getId());
        customerMapstruct.mapToCustomer(customerDTO, customer);
    }

    @Override
    public List<CustomerDTO> getActiveCustomers() {
        return customerMapstruct.mapToCustomerDTOList(customerServices.getActiveCustomers());
    }

    @Override
    public List<CustomerDTO> searchCustomers(String searchCase) throws CustomerNotFoundException {
        return customerMapstruct.mapToCustomerDTOList(customerServices.searchCustomers(searchCase));
    }

    @Override
    public CustomerDTO getCustomer(Integer id) throws CustomerNotFoundException {
        return customerMapstruct.mapToCustomerDTO(customerServices.getCustomer(id));
    }

    @Override
    public void deleteCustomer(Integer id) throws CustomerNotFoundException {
        customerServices.deleteCustomer(id);
    }

    @Override
    public List<CustomerDTO> getDeletedCustomers() {
        return customerMapstruct.mapToCustomerDTOList(customerServices.getDeletedCustomers());
    }

    @Override
    public void saveData(String fileName, FileType fileType) throws FileException {
        customerServices.saveData(fileName, fileType);
    }

    @Override
    public void loadData(String fileName, FileType fileType) throws FileException {
        customerServices.loadData(fileName, fileType);
    }

    @Override
    public void initialData() {
        customerServices.initialData();
    }

    @Override
    public void saveOnExit() {
        customerServices.saveOnExit();
    }

    @Override
    public void addData(String fileName) throws FileException {
        customerServices.addData(fileName);
    }
}
