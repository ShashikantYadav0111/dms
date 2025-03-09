package com.project.dairy_management_system.Repository;

import com.project.dairy_management_system.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Item,Long> {
}
