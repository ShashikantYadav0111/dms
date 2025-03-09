package com.project.dairy_management_system.Repository;

import com.project.dairy_management_system.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
