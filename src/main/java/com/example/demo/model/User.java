package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*; // Required for @Data and @Builder
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data // Generates getters/setters (fixes getEmail, getPassword, getRoles)
@Builder // Fixes User.builder() in UserServiceImpl
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles;
    private LocalDateTime createdAt;
}