package com.project.dairy_management_system.dto;

public record ItemRequest(
        Long customerId,
        Long productId
) {
}
