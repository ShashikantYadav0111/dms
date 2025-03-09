package com.project.dairy_management_system.Repository;

import com.project.dairy_management_system.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
