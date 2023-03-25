package com.example.application.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(of = "username")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;
}
