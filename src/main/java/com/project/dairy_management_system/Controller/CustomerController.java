package com.project.dairy_management_system.Controller;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.Service.Customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
       Customer savedCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(savedCustomer,HttpStatus.CREATED);
    }

    @GetMapping("/get-customer")
    public List<Customer> getCustomer(){
        return customerService.getCustomer();
    }

    @PutMapping("/update-customer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId,@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.updateCustomer(customerId,customer),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-customer/{customerId}")
    public boolean deleteCustomer(@PathVariable Long customerId){
        return customerService.deleteCustomer(customerId);
    }
}
