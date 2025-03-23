package com.project.dairy_management_system.mapper;

import com.project.dairy_management_system.DTO.CustomerDto;
import com.project.dairy_management_system.Entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface CustomerMapper{

    @Mapping(target = "customerId",source = "id")
    @Mapping(target = "fullName",expression = "java(customer.getFirstName()+ \" \"+customer.getLastName())")
    CustomerDto toDto(Customer customer);

    @Mapping(target = "firstName",expression = "java(dto.getFullName().split(\" \")[0])")
    @Mapping(target = "lastName",expression = "java(dto.getFullName().split(\" \")[1])")
    Customer toEntity(CustomerDto dto);
}




//@Component
//public class CustomerMapper {
//    public CustomerDto toDto(Customer customer){
//        return CustomerDto.builder()
//                .customerId(customer.getId())
//                .fullName(customer.getFirstName()+" "+customer.getLastName())
//                .address(customer.getAddress())
//                .phoneNo(customer.getPhoneNo())
//                .build();
//    }
//
//    public Customer toEntity(CustomerDto customerDto){
//        return Customer.builder()
//                .id(customerDto.getCustomerId())
//                .firstName(customerDto.getFullName().split(" ")[0])
//                .lastName(customerDto.getFullName().split(" ")[1])
//                .address(customerDto.getAddress())
//                .phoneNo(customerDto.getPhoneNo())
//        .build();
//    }
//}
