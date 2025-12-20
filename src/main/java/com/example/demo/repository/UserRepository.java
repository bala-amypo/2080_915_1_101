package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Primary method for login, registration checks, and security context [cite: 127]
    Optional<User> findByEmail(String email);
}