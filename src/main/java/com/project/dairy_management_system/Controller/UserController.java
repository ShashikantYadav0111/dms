package com.project.dairy_management_system.Controller;

import com.project.dairy_management_system.Entity.Users;
import com.project.dairy_management_system.Service.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UsersService usersService;
    @PostMapping("/register")
    public Users createUser(@RequestBody Users user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
        return usersService.registerUser(user);
    }
    @PostMapping("/login")
    public String Login(@RequestBody Users user){
        System.out.println("Here");
        String authToken = usersService.verify(user);
        System.out.println(authToken);
        return authToken;
    }
}
