package com.project.dairy_management_system.Repository;

import com.project.dairy_management_system.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    public Users findByUsername(String username);
}
