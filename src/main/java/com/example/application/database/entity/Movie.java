package com.example.application.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = "title")
@ToString(exclude = {"moviePeople"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "movie", schema = "public")
public class Movie implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private LocalDate releaseDate;

    @Builder.Default
    @OneToMany(mappedBy = "movie")
    private List<MoviePerson> moviePeople = new ArrayList<>();
}
