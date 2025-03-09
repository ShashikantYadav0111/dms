package com.project.dairy_management_system.Service.Employee;

import com.project.dairy_management_system.Entity.Employee;


public interface EmployeeService {
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(Long employeeId);
    String deleteEmployee(Long employeeId);
    Employee updateEmployee(Long employeeId,Employee employee);

}
