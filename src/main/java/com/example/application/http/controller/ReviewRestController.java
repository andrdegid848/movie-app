package com.example.application.http.controller;

import com.example.application.dto.review.ReviewCreateEditDto;
import com.example.application.dto.review.ReviewReadDto;
import com.example.application.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewService reviewService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewReadDto> findAll() {
        return reviewService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewReadDto findById(@PathVariable("id") Long id) {
        return reviewService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/movie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewReadDto> findAllByMovieId(@PathVariable("id") Integer id) {
        return reviewService.findAllByMovieId(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewReadDto create(@Validated @RequestBody ReviewCreateEditDto reviewCreateEditDto) {
        return reviewService.create(reviewCreateEditDto);
    }

    @PutMapping(value = "/{id}")
    public ReviewReadDto update(@PathVariable("id") Long id,
                                @Validated @RequestBody ReviewCreateEditDto reviewCreateEditDto) {
        return reviewService.update(id, reviewCreateEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!reviewService.delete(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
