package com.project.dairy_management_system.Service.Customer;

import com.project.dairy_management_system.Entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Long customerId,Customer customer);
    boolean deleteCustomer(Long customerId);
    List<Customer> getCustomer();
}
