package com.project.dairy_management_system.Service.Customers;

import com.project.dairy_management_system.DTO.CustomerDto;
import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.Exception.DuplicatePhoneNumberException;
import com.project.dairy_management_system.Repository.CustomerRepository;
import com.project.dairy_management_system.Service.Redis.RedisService;
import com.project.dairy_management_system.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerDto addCustomer(Customer customer) throws DuplicatePhoneNumberException {
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto dto) {
        Optional<Customer> fetchedCustomer = customerRepository.findById(id);
        Customer customer = null;
        if(fetchedCustomer.isPresent()){
            customer = fetchedCustomer.get();
        }
        assert customer != null;
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public String deleteCustomer(Long id) {
        Optional<Customer> fetchedCustomer = customerRepository.findById(id);
        Customer customer = null;
        if(fetchedCustomer.isPresent()){
            customer = fetchedCustomer.get();
        }
        assert customer != null;
        customerRepository.delete(customer);
        if(customerRepository.findById(id).isEmpty()){
            return "Customer Deleted";
        }
        return null;
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        CustomerDto cachedProduct = redisService.getData(id.toString());
        if (cachedProduct != null) {
            return  cachedProduct;
        }
        Optional<Customer> customer = customerRepository.findById(id);
        CustomerDto dto = null;
        if(customer.isPresent()){
            dto = customerMapper.toDto(customer.get());
        }
        redisService.saveData(id.toString(),dto,10);
        return dto;
    }
}
