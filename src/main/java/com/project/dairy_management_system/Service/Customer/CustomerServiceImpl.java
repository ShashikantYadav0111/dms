package com.project.dairy_management_system.Service.Customer;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {
        Optional<Customer> fetchedCustomer = customerRepository.findById(customerId);
        Customer updatedCustomer = fetchedCustomer.get();
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setAddress(customer.getAddress());
        updatedCustomer.setPhoneNo(customer.getPhoneNo());
        return customerRepository.save(updatedCustomer);
    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        Customer fetchedCustomer = customerRepository.findById(customerId).get();
        customerRepository.delete(fetchedCustomer);
        return !customerRepository.findById(customerId).isPresent();
    }

    @Override
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }
}
