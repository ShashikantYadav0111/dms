package com.project.dairy_management_system.Repository;

import com.project.dairy_management_system.Entity.Cart;
import com.project.dairy_management_system.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByCustomer_CustomerId(Long customerId);
}
