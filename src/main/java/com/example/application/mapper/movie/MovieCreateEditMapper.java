package com.example.application.mapper.movie;

import com.example.application.database.entity.Movie;
import com.example.application.dto.movie.MovieCreateEditDto;
import com.example.application.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MovieCreateEditMapper implements Mapper<MovieCreateEditDto, Movie> {

    @Override
    public Movie map(MovieCreateEditDto object) {
        Movie movie = new Movie();
        copy(object, movie);
        return movie;
    }

    @Override
    public Movie map(MovieCreateEditDto fromObject, Movie toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(MovieCreateEditDto movieCreateEditDto, Movie movie) {
        movie.setGenre(movieCreateEditDto.getGenre());
        movie.setTitle(movieCreateEditDto.getTitle());
        movie.setReleaseDate(movieCreateEditDto.getReleaseDate());
    }
}
