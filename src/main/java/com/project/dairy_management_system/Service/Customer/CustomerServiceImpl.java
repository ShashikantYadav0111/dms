package com.project.dairy_management_system.Service.Customer;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.Repository.CustomerRepository;
import com.project.dairy_management_system.dto.CustomerDto;
import com.project.dairy_management_system.dto.CustomerRegister;
import com.project.dairy_management_system.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public CustomerDto addCustomer(CustomerRegister register) {
        Customer customer = Customer.builder()
                .firstName(register.fullName().split(" ")[0])
                .lastName(register.fullName().split(" ")[1])
                .address(register.address())
                .phoneNo(register.phoneNo())
                .email(register.email())
                .password(register.password())
                .build();
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDto getById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(value -> customerMapper.toDto(value)).orElse(null);
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Optional<Customer> fetchedCustomer = customerRepository.findById(id);
        if(fetchedCustomer.isPresent()){
            Customer customer = Customer.builder()
                    .customerId(id)
                    .email(customerDto.email())
                    .phoneNo(customerDto.phoneNo())
                    .firstName(customerDto.fullName().split(" ")[0])
                    .lastName(customerDto.fullName().split( " ")[1])
                    .address(customerDto.address())
                    .build();
            return customerMapper.toDto(customerRepository.save(customer));
        }
        return null;
    }

    @Override
    public Boolean deleteCustomer(Long id) {
        customerRepository.delete(customerRepository.findById(id).get());
        return customerRepository.findById(id).isEmpty();

    }
}
