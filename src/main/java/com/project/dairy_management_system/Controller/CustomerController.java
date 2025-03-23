package com.project.dairy_management_system.Controller;


import com.project.dairy_management_system.DTO.CustomerDto;
import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.Service.Customers.CustomerService;
import com.project.dairy_management_system.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> createUser(@RequestBody CustomerDto customerDto){
        Customer customer = customerMapper.toEntity(customerDto);
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id,@RequestBody CustomerDto dto){
        return new ResponseEntity<>(customerService.updateCustomer(id,dto),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> getCustomer(){
        return new ResponseEntity<>(customerService.getCustomers(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        return new ResponseEntity<>(customerService.deleteCustomer(id),HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Long id){
        return new ResponseEntity<>( customerService.getCustomerById(id),HttpStatus.OK);
    }
}
