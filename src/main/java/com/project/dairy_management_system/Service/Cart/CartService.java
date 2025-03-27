package com.project.dairy_management_system.Service.Cart;

import com.project.dairy_management_system.Entity.CartItem;
import com.project.dairy_management_system.dto.ItemDto;
import com.project.dairy_management_system.dto.ItemRequest;

import java.util.List;

public interface CartService{
    String addItem(ItemRequest itemRequest);

    String removeItem(ItemRequest itemRequest);

    List<ItemDto> getCart(Long id);
}
