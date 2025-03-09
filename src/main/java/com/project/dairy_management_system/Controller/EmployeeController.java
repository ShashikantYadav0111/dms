package com.project.dairy_management_system.Controller;

import com.project.dairy_management_system.Entity.Employee;
import com.project.dairy_management_system.Service.Employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping("/add-employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }
    @GetMapping("/get-employee/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @DeleteMapping("/delete-employee/{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("update-employee/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId,@RequestBody Employee employee){
        return employeeService.updateEmployee(employeeId,employee);
    }
}
