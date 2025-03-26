package com.project.dairy_management_system.Service.Customer;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.dto.CustomerDto;
import com.project.dairy_management_system.dto.CustomerRegister;

import java.util.List;

public interface CustomerService {
    CustomerDto addCustomer(CustomerRegister register);

    CustomerDto getById(Long id);

    List<CustomerDto> getAll();

    CustomerDto updateCustomer(Long id, CustomerDto dto);

    Boolean deleteCustomer(Long id);
}
