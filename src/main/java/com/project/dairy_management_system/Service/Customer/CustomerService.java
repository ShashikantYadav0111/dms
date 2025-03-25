package com.project.dairy_management_system.Service.Customer;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto addCustomer(CustomerDto dto);
    CustomerDto updateCustomer(Long customerId,CustomerDto dto);
    boolean deleteCustomer(Long customerId);
    List<CustomerDto> getCustomer();
}
