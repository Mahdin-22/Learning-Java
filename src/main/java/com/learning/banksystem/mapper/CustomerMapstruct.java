package com.learning.banksystem.mapper;

import com.learning.banksystem.dto.CustomerDTO;
import com.learning.banksystem.dto.LegalCustomerDTO;
import com.learning.banksystem.dto.RealCustomerDTO;
import com.learning.banksystem.model.Customer;
import com.learning.banksystem.model.LegalCustomer;
import com.learning.banksystem.model.RealCustomer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapstruct {
    default CustomerDTO mapToCustomerDTO(Customer customer) {
        if (customer instanceof RealCustomer) {
            return mapToRealCustomerDTO((RealCustomer) customer);
        } else {
            return mapToLegalCustomerDTO((LegalCustomer) customer);
        }
    };
    RealCustomerDTO mapToRealCustomerDTO(RealCustomer realCustomer);
    LegalCustomerDTO mapToLegalCustomerDTO(LegalCustomer legalCustomer);
    List<CustomerDTO> mapToCustomerDTOList(List<Customer> customerList);

    default void mapToCustomer (CustomerDTO customerDTO, Customer customer) {
        if (customerDTO instanceof RealCustomerDTO) {
            mapToRealCustomer((RealCustomerDTO) customerDTO, (RealCustomer) customer);
        } else {
            mapToLegalCustomer((LegalCustomerDTO) customerDTO, (LegalCustomer) customer);
        }
    }
    default Customer mapToCustomer (CustomerDTO customerDTO) {
        if (customerDTO instanceof RealCustomerDTO) {
            return mapToRealCustomer((RealCustomerDTO) customerDTO, new RealCustomer(null, null, null));
        } else {
            return mapToLegalCustomer((LegalCustomerDTO) customerDTO, new LegalCustomer(null, null, null));
        }
    }

    default LegalCustomer mapToLegalCustomer (LegalCustomerDTO legalCustomerDTO, LegalCustomer legalCustomer) {
        legalCustomer.setFullName(legalCustomerDTO.getFullName());
        legalCustomer.setNumber(legalCustomerDTO.getNumber());
        legalCustomer.setEmail(legalCustomerDTO.getEmail());
        legalCustomer.setJobTitle(legalCustomerDTO.getJobTitle());
        return legalCustomer;
    }
    default RealCustomer mapToRealCustomer (RealCustomerDTO realCustomerDTO, RealCustomer realCustomer) {
        realCustomer.setFullName(realCustomerDTO.getFullName());
        realCustomer.setNumber(realCustomerDTO.getNumber());
        realCustomer.setEmail(realCustomerDTO.getEmail());
        realCustomer.setBirthDate(realCustomerDTO.getBirthDate());
        return realCustomer;
    }
}
