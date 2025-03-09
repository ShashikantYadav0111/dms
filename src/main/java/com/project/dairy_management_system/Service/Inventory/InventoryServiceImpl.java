package com.project.dairy_management_system.Service.Inventory;

import com.project.dairy_management_system.Entity.Employee;
import com.project.dairy_management_system.Entity.Item;
import com.project.dairy_management_system.Repository.EmployeeRepository;
import com.project.dairy_management_system.Repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;


    @Override
    public Item addItem(Item item) {
        return inventoryRepository.save(item);
    }

    @Override
    public List<Item> getItem() {
        return inventoryRepository.findAll();
    }

    @Override
    public String deleteItem(Long itemId) {
        String status = "Item Not Found!";
        Optional<Item> fetchedItem = inventoryRepository.findById(itemId);

        if(fetchedItem.isPresent()){
            inventoryRepository.delete(fetchedItem.get());
            return "Item Deleted";
        }
        return status;
    }

    @Override
    public Item updateItem(Long itemId, Item item) {
        Optional<Item> fetchedItem = inventoryRepository.findById(itemId);

        if(fetchedItem.isPresent()){
            fetchedItem.get().setItemName(item.getItemName());
            fetchedItem.get().setItemQty(item.getItemQty());
        }
        return
                inventoryRepository.save(fetchedItem.get());
    }
}
