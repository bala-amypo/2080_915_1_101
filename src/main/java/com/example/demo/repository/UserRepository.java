package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // This fixed Error 2 & 3
    Optional<User> findByEmail(String email);

    // This is also needed for your Security Logic
    Optional<User> findByUsername(String username);
}