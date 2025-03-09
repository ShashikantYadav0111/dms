package com.project.dairy_management_system.Controller;

import com.project.dairy_management_system.Entity.Item;
import com.project.dairy_management_system.Service.Inventory.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private InventoryService inventoryService;

    @PostMapping("/add-item")
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        return new ResponseEntity<>(inventoryService.addItem(item), HttpStatus.CREATED);
    }
    @GetMapping("/get-items")
    public List<Item> getItem(){
        return inventoryService.getItem();
    }

    @DeleteMapping("/delete-item/{itemId}")
    public String deleteItem(@PathVariable Long itemId){
        return inventoryService.deleteItem(itemId);
    }

    @PutMapping("update-item/{itemId}")
    public Item updateItem(@PathVariable Long itemId,@RequestBody Item item){
        return inventoryService.updateItem(itemId,item);
    }
}
