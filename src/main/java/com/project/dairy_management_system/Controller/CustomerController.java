package com.project.dairy_management_system.Controller;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.Service.Customer.CustomerService;
import com.project.dairy_management_system.dto.CustomerDto;
import com.project.dairy_management_system.dto.CustomerRegister;
import com.project.dairy_management_system.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper mapper;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerRegister register){
        return new ResponseEntity<>(customerService.addCustomer(register),HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Long id){
        return new ResponseEntity<>(customerService.getById(id),HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<List<CustomerDto>> getAll(){
        return new ResponseEntity<>(customerService.getAll(),HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id,@RequestBody CustomerDto dto){
        return new ResponseEntity<>(customerService.updateCustomer(id,dto),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable Long id){
        return new ResponseEntity<>(customerService.deleteCustomer(id),HttpStatus.OK);
    }

}
