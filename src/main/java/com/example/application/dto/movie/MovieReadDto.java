package com.example.application.dto.movie;

import com.example.application.database.entity.Genre;
import lombok.Value;

import java.time.LocalDate;

@Value
public class MovieReadDto {
    Integer id;
    String title;
    Genre genre;
    LocalDate releaseDate;
}
