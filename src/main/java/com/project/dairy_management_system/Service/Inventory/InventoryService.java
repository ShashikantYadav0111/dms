package com.project.dairy_management_system.Service.Inventory;

import com.project.dairy_management_system.Entity.Employee;
import com.project.dairy_management_system.Entity.Item;

import java.util.List;


public interface InventoryService {
    Item addItem(Item item);
    List<Item> getItem();
    String deleteItem(Long itemId);
    Item updateItem(Long itemId,Item item);

}
