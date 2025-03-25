package com.project.dairy_management_system.Controller;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.Service.Customer.CustomerService;
import com.project.dairy_management_system.dto.CustomerDto;
import com.project.dairy_management_system.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper mapper;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto dto){
        return new ResponseEntity<>(customerService.addCustomer(dto),HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<CustomerDto> getCustomer(){
        return customerService.getCustomer();
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDto dto){
        return new ResponseEntity<>(customerService.updateCustomer(customerId,dto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable Long customerId){
        return new ResponseEntity<>(customerService.deleteCustomer(customerId),HttpStatus.OK);
    }
}
