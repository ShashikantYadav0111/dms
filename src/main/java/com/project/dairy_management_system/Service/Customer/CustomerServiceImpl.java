package com.project.dairy_management_system.Service.Customer;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.Repository.CustomerRepository;
import com.project.dairy_management_system.dto.CustomerDto;
import com.project.dairy_management_system.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public CustomerDto addCustomer(CustomerDto dto) {
        Customer customer = customerMapper.toEntity(dto);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto dto) {
        Optional<Customer> fetchedCustomer = customerRepository.findById(customerId);

        if(fetchedCustomer.isPresent()){
            Customer updatedCustomer = Customer.builder()
                    .id(customerId)
                    .firstName(dto.getFullName().split(" ")[0])
                    .lastName(dto.getFullName().split(" ")[1])
                    .address(dto.getAddress())
                    .phoneNo(dto.getPhoneNo())
                    .build();
            return customerMapper.toDto(customerRepository.save(updatedCustomer));
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        if(customerRepository.findById(customerId).isPresent()){
            customerRepository.delete(customerRepository.findById(customerId).get());
        }
        return customerRepository.findById(customerId).isPresent();
    }

    @Override
    public List<CustomerDto> getCustomer() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }
}
