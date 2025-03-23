package com.project.dairy_management_system.Service.Customers;

import com.project.dairy_management_system.DTO.CustomerDto;
import com.project.dairy_management_system.Entity.Customer;

import java.util.List;

public interface CustomerService {
     CustomerDto addCustomer(Customer customer);
     List<CustomerDto> getCustomers();
     CustomerDto updateCustomer(Long id, CustomerDto dto);
     String deleteCustomer(Long id);
     CustomerDto getCustomerById(Long id);
}
