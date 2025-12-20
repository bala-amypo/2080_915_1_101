package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 75]

    @Column(nullable = false)
    private String name; // [cite: 76, 81]

    @Column(unique = true, nullable = false)
    private String email; // [cite: 77, 81]

    @Column(nullable = false)
    private String password; // [cite: 78, 81]

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles; // 

    private LocalDateTime createdAt; // [cite: 80]
}