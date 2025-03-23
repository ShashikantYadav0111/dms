package com.project.dairy_management_system.Service.Users;

import com.project.dairy_management_system.Entity.Users;
import org.springframework.stereotype.Service;


public interface UsersService {
    public Users registerUser(Users user);
    public String verify(Users user);
}
