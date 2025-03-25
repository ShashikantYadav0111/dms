package com.project.dairy_management_system.dto;

import lombok.*;
import org.springframework.stereotype.Component;


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
