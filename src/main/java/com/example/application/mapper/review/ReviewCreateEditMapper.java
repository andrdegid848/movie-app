package com.example.application.mapper.review;

import com.example.application.database.entity.Movie;
import com.example.application.database.entity.Review;
import com.example.application.database.entity.User;
import com.example.application.database.repository.MovieRepository;
import com.example.application.database.repository.UserRepository;
import com.example.application.dto.review.ReviewCreateEditDto;
import com.example.application.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReviewCreateEditMapper implements Mapper<ReviewCreateEditDto, Review> {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Override
    public Review map(ReviewCreateEditDto object) {
        Review review = new Review();
        copy(object, review);

        return review;
    }

    @Override
    public Review map(ReviewCreateEditDto fromObject, Review toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ReviewCreateEditDto reviewCreateEditDto, Review review) {
        review.setMovie(getMovie(reviewCreateEditDto.getMovieId()));
        review.setUser(getUser(reviewCreateEditDto.getUserId()));
        review.setText(reviewCreateEditDto.getText());
        review.setFilmScore(reviewCreateEditDto.getFilmScore());
    }

    private Movie getMovie(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(movieRepository::findById)
                .orElse(null);
    }

    private User getUser(Long id) {
        return Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .orElse(null);
    }
}
