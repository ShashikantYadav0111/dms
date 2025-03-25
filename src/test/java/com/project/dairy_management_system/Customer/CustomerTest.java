package com.project.dairy_management_system.Customer;

import com.project.dairy_management_system.Entity.Customer;
import com.project.dairy_management_system.Repository.CustomerRepository;
import com.project.dairy_management_system.Service.Customer.CustomerServiceImpl;
import com.project.dairy_management_system.dto.CustomerDto;
import com.project.dairy_management_system.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.quality.Strictness;  // Correct import

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CustomerTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void testCreateUser(){
        CustomerDto requestDto = CustomerDto.builder()
                .customerId(1L)
                .fullName("Shashikant Yadav")
                .address("2623")
                .phoneNo("976521212")
                .build();

        Customer customerEntity = Customer.builder()
                .id(1L)
                .firstName("Shashikant")
                .lastName("Yadav")
                .address("2623")
                .phoneNo("976521212")
                .build();

        CustomerDto expectedDto = CustomerDto.builder()
                .customerId(1L)
                .fullName("Shashikant Yadav")
                .address("2623")
                .phoneNo("976521212")
                .build();

        //Mapper Behaviour
        when(customerMapper.toEntity(requestDto)).thenReturn(customerEntity);
        when(customerMapper.toDto(customerEntity)).thenReturn(expectedDto);

        //Repo Behaviour
        when(customerRepository.save(any(Customer.class))).thenReturn(customerEntity);

        CustomerDto actualDto = customerService.addCustomer(requestDto);

        assertNotNull(actualDto);
        assertEquals(expectedDto.getFullName(), actualDto.getFullName());
        assertEquals(expectedDto.getPhoneNo(), actualDto.getPhoneNo());

        // Verify interactions

        verify(customerMapper, times(1)).toEntity(requestDto);
        verify(customerMapper, times(1)).toDto(customerEntity);
        verify(customerRepository, times(1)).save(customerEntity);
    }

    @Test
    void getAllCustomers_ShouldReturnListOfCustomerDtos_WhenCustomersExist() {
        //given
        Customer customer1 = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .address("2345")
                .phoneNo("9876543212")
                .build();

        Customer customer2 = Customer.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .address("2322")
                .phoneNo("9876543222")
                .build();

        List<Customer> customers = Arrays.asList(customer1, customer2);

        CustomerDto dto1 = CustomerDto.builder()
                .customerId(1L)
                .fullName("John Doe")
                .address("2345")
                .phoneNo("9876543212")
                .build();

        CustomerDto dto2 = CustomerDto.builder()
                .customerId(2L)
                .fullName("Jane Smith")
                .address("2322")
                .phoneNo("9876543222")
                .build();

        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.toDto(customer1)).thenReturn(dto1);
        when(customerMapper.toDto(customer2)).thenReturn(dto2);

        // When
        List<CustomerDto> result = customerService.getCustomer();

        //Then
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullName());
        assertEquals("Jane Smith", result.get(1).getFullName());
        verify(customerRepository, times(1)).findAll();
        verify(customerMapper, times(1)).toDto(customer1);
        verify(customerMapper, times(1)).toDto(customer2);
    }

    @Test
    void deleteCustomer_ShouldDeleteCustomer_WhenCustomerExists(){
        Long customerId = 1L;
        Customer customer = Customer.builder()
                .id(1L)
                .firstName("Shashikant")
                .lastName("yadav")
                .address("2222")
                .phoneNo("9876543210")
                .build();
        CustomerDto dto = CustomerDto.builder()
                .customerId(1L)
                .fullName("Shashikant yadav")
                .address("2222")
                .phoneNo("9876543210")
                .build();


        //Repo
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(customer);

        //When
        customerService.deleteCustomer(customerId);

        //then
        verify(customerRepository,times(3)).findById(customerId);
        verify(customerRepository,times(1)).delete(customer);

    }

    @Test
    void updateCustomer_ShouldReturnUpdatedCustomerDto_WhenCustomerExists() {
        // Given
        Long customerId = 1L;
        CustomerDto updateDto = CustomerDto.builder()
                .customerId(customerId)
                .fullName("Updated Name")
                .address("New Address")
                .phoneNo("987654321")
                .build();

        Customer existingCustomer = Customer.builder()
                .id(customerId)
                .firstName("Original")
                .lastName("Name")
                .address("Old Address")
                .phoneNo("123456789")
                .build();

        Customer updatedCustomer = Customer.builder()
                .id(customerId)
                .firstName("Updated")
                .lastName("Name")
                .address("New Address")
                .phoneNo("987654321")
                .build();

        CustomerDto expectedDto = CustomerDto.builder()
                .customerId(customerId)
                .fullName("Updated Name")
                .address("New Address")
                .phoneNo("987654321")
                .build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerMapper.toEntity(updateDto)).thenReturn(updatedCustomer);
        when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);
        when(customerMapper.toDto(updatedCustomer)).thenReturn(expectedDto);

        // When
        CustomerDto result = customerService.updateCustomer(customerId, updateDto);

        // Then
        assertEquals("Updated Name", result.getFullName());
        assertEquals("New Address", result.getAddress());
        assertEquals("987654321", result.getPhoneNo());
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).save(updatedCustomer);
        verify(customerMapper, times(1)).toEntity(updateDto);
        verify(customerMapper, times(1)).toDto(updatedCustomer);
    }

    @Test
    void updateCustomer_ShouldThrowException_WhenCustomerNotFound() {
        // Given
        Long customerId = 99L;
        CustomerDto updateDto = CustomerDto.builder()
                .customerId(customerId)
                .fullName("Non-existent")
                .build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RuntimeException.class, () -> {
            customerService.updateCustomer(customerId, updateDto);
        });
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, never()).save(any());
        verify(customerMapper, never()).toEntity(any());
    }

}
