package com.example.application.mapper.movie;

import com.example.application.database.entity.Movie;
import com.example.application.dto.movie.MovieReadDto;
import com.example.application.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MovieReadMapper implements Mapper<Movie, MovieReadDto> {

    @Override
    public MovieReadDto map(Movie object) {
        return new MovieReadDto(
                object.getId(),
                object.getTitle(),
                object.getGenre(),
                object.getReleaseDate()
        );
    }
}
