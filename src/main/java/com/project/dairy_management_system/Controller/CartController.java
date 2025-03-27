package com.project.dairy_management_system.Controller;

import com.project.dairy_management_system.Entity.CartItem;
import com.project.dairy_management_system.Service.Cart.CartService;
import com.project.dairy_management_system.dto.ItemDto;
import com.project.dairy_management_system.dto.ItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping("/addItem")
    public ResponseEntity<String> addItem(@RequestBody ItemRequest itemRequest){
        return new ResponseEntity<>(cartService.addItem(itemRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/removeItem")
    public ResponseEntity<String> removeItem(@RequestBody ItemRequest itemRequest){
        return new ResponseEntity<>(cartService.removeItem(itemRequest), HttpStatus.OK);
    }

    @GetMapping("/getList/{id}")
    public ResponseEntity<List<ItemDto>> getCart(@PathVariable Long id){
        return new ResponseEntity<>(cartService.getCart(id), HttpStatus.OK);
    }
}
