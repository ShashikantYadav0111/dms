package com.project.dairy_management_system.Repository;

import com.project.dairy_management_system.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    Optional<CartItem> findTop1ByProduct_ProductId(Long productId);
}
