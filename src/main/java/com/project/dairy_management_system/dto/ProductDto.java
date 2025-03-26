package com.project.dairy_management_system.dto;

import java.math.BigDecimal;

public record ProductDto (
        Long productId,
        String productName,
        int stockCount,
        BigDecimal price,
        String createdAt
){

}
