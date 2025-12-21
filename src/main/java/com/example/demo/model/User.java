package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users") // [cite: 73]
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 75]
    private String name; // [cite: 76]
    @Column(unique = true)
    private String email; // [cite: 77]
    private String password; // [cite: 78]
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles; // [cite: 79, 82]
    private LocalDateTime createdAt; // [cite: 80]
}