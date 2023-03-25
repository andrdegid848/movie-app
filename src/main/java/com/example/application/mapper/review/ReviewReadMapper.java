package com.example.application.mapper.review;

import com.example.application.database.entity.Review;
import com.example.application.dto.movie.MovieReadDto;
import com.example.application.dto.review.ReviewReadDto;
import com.example.application.dto.user.UserReadDto;
import com.example.application.mapper.Mapper;
import com.example.application.mapper.movie.MovieReadMapper;
import com.example.application.mapper.user.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReviewReadMapper implements Mapper<Review, ReviewReadDto> {

    private final MovieReadMapper movieReadMapper;
    private final UserReadMapper userReadMapper;

    @Override
    public ReviewReadDto map(Review object) {
        MovieReadDto movieReadDto = Optional.ofNullable(object.getMovie())
                .map(movieReadMapper::map)
                .orElse(null);

        UserReadDto userReadDto = Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElse(null);

        return new ReviewReadDto(
                object.getId(),
                movieReadDto,
                userReadDto,
                object.getText(),
                object.getFilmScore()
        );
    }
}
