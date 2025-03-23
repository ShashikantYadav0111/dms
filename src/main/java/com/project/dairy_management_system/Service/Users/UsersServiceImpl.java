package com.project.dairy_management_system.Service.Users;

import com.project.dairy_management_system.Entity.Users;
import com.project.dairy_management_system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;

    @Override
    public Users registerUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public String verify(Users user) {
        userRepository.findByUsername(user.getUsername());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }else{
            return "Authentication Failed";
        }
    }
}
