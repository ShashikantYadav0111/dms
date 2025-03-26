package com.project.dairy_management_system.dto;

public record CustomerRegister(
        String FullName,
        String email,
        String phoneNo,
        String password,
        String address
) {
}
