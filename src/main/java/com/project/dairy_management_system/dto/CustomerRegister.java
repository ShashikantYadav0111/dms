package com.project.dairy_management_system.dto;

public record CustomerRegister(
        String fullName,
        String email,
        String phoneNo,
        String password,
        String address
) {
}
