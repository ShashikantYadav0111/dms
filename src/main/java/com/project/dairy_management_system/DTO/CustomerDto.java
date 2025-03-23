package com.project.dairy_management_system.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private Long customerId;
    private String fullName;
    private String address;
    private String phoneNo;
}
