package com.project.dairy_management_system.mapper;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "fullName",expression = "java(customer.getFirstName() +\" \"+ customer.getLastName())")
    @Mapping(target = "customerId",source = "id")
    CustomerDto toDto(Customer customer);

    @Mapping(target = "firstName",expression = "java(dto.getFullName().split(\" \")[0])")
    @Mapping(target = "lastName",expression = "java(dto.getFullName().split(\" \")[1])")
    @Mapping(target = "id",source = "customerId")
    Customer toEntity(CustomerDto dto);

}
