package com.project.dairy_management_system.mapper;

import com.project.dairy_management_system.Entity.Product;
import com.project.dairy_management_system.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
}
