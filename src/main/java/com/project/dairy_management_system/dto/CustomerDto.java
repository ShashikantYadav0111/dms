package com.project.dairy_management_system.dto;



public record CustomerDto (
        Long customerId,
        String fullName,
        String address,
        String phoneNo,
        String email
){}
