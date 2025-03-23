package com.project.dairy_management_system.Service.Users;

import com.project.dairy_management_system.Entity.UserPrincipal;
import com.project.dairy_management_system.Entity.Users;
import com.project.dairy_management_system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Not Found!!");
        }

        return new UserPrincipal(user);

    }
}
