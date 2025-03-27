package com.project.dairy_management_system.mapper;

import com.project.dairy_management_system.Entity.CartItem;
import com.project.dairy_management_system.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "productName",expression = "java(item.getProduct().getProductName())")
    @Mapping(target = "quantity",expression = "java(item.getQuantity())")
    @Mapping(target = "cost",expression = "java(java.math.BigDecimal.valueOf(item.getQuantity()).multiply(item.getProduct().getPrice()))")
    ItemDto toDto(CartItem item);
}
