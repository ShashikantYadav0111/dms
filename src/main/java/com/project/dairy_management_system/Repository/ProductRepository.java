package com.project.dairy_management_system.Repository;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductNameContainingIgnoreCase(String keyword);
}
