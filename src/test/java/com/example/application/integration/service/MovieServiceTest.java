package com.example.application.integration.service;

import com.example.application.database.entity.Genre;
import com.example.application.dto.movie.MovieCreateEditDto;
import com.example.application.dto.movie.MovieReadDto;
import com.example.application.integration.IntegrationTestBase;
import com.example.application.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class MovieServiceTest extends IntegrationTestBase {

    private static final Integer MOVIE_ID = 1;
    private static final Integer FAKE_MOVIE_ID = -1;
    private final MovieService movieService;

    @Test
    void findAll() {
        List<MovieReadDto> movies = movieService.findAll();
        assertThat(movies).hasSize(5);
    }

    @Test
    void findById() {
        Optional<MovieReadDto> movie = movieService.findById(MOVIE_ID);
        assertTrue(movie.isPresent());
        movie.ifPresent(it -> {
            assertEquals(MOVIE_ID, it.getId());
            assertEquals("The Matrix", it.getTitle());
            assertEquals(Genre.ACTION, it.getGenre());
            assertEquals(LocalDate.of(1999, 3, 31), it.getReleaseDate());

        });
    }

    @Test
    void update() {
        MovieCreateEditDto movieCreateEditDto = new MovieCreateEditDto(
                "The Film",
                Genre.ACTION,
                LocalDate.of(2000, 1, 1)
        );

        Optional<MovieReadDto> updateMovie = movieService.update(MOVIE_ID, movieCreateEditDto);

        updateMovie.ifPresent(it -> {
            assertEquals(movieCreateEditDto.getTitle(), it.getTitle());
            assertEquals(movieCreateEditDto.getGenre(), it.getGenre());
            assertEquals(movieCreateEditDto.getReleaseDate(), it.getReleaseDate());
        });
    }

    @Test
    void delete() {
        assertFalse(movieService.delete(FAKE_MOVIE_ID));
    }
}