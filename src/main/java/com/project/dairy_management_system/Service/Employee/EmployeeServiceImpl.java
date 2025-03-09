package com.project.dairy_management_system.Service.Employee;

import com.project.dairy_management_system.Entity.Employee;
import com.project.dairy_management_system.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    @Override
    public String deleteEmployee(Long employeeId) {
        String status = "";
        employeeRepository.deleteById(employeeId);
        if(!employeeRepository.findById(employeeId).isPresent()){
        status+= "Deleted Employee." ;
        }else{
            status+="Some thing went wrong could not delete Employee";
        }
        return status;
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee employee) {
        Employee fetchedEmployee = employeeRepository.findById(employeeId).get();
        fetchedEmployee.setFirstName(employee.getFirstName());
        fetchedEmployee.setLastName(employee.getLastName());
        fetchedEmployee.setPhoneNo(employee.getPhoneNo());
        return employeeRepository.save(fetchedEmployee);
    }
}
