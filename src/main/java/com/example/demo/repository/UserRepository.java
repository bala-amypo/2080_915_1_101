package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Fixes the findByEmail errors in UserServiceImpl
    Optional<User> findByEmail(String email);

    // Needed for login via username
    Optional<User> findByUsername(String username);
}