package com.example.application.integration.service;

import com.example.application.dto.review.ReviewReadDto;
import com.example.application.integration.IntegrationTestBase;
import com.example.application.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class ReviewServiceTest extends IntegrationTestBase {

    private final static Long REVIEW_ID = 1L;
    private final static Integer MOVIE_ID = 1;
    private final ReviewService reviewService;

    @Test
    void findAll() {
        List<ReviewReadDto> reviews = reviewService.findAll();
        assertThat(reviews).hasSize(10);
    }

    @Test
    void findById() {
        Optional<ReviewReadDto> review = reviewService.findById(REVIEW_ID);
        assertTrue(review.isPresent());
        review.ifPresent(it -> {
            assertEquals("Good", it.getText());
            assertEquals(8, it.getFilmScore());
        });
    }

    @Test
    void findAllByMovieId() {
        List<ReviewReadDto> reviewsByMovieId = reviewService.findAllByMovieId(MOVIE_ID);
        assertThat(reviewsByMovieId).hasSize(2);
    }
}