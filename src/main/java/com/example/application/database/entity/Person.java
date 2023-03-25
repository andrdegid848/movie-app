package com.example.application.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"moviePeople"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Person implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private PersonRole role;

    @Builder.Default
    @OneToMany(mappedBy = "person")
    private List<MoviePerson> moviePeople = new ArrayList<>();
}
