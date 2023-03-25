package com.example.application.dto.movie;

import com.example.application.database.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Value;

import java.time.LocalDate;

@Value
public class MovieCreateEditDto {

    @NotBlank
    String title;

    Genre genre;

    @Past
    LocalDate releaseDate;
}
