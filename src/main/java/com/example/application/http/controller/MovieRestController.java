package com.example.application.http.controller;

import com.example.application.dto.movie.MovieCreateEditDto;
import com.example.application.dto.movie.MovieReadDto;
import com.example.application.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MovieReadDto> findAll() {
        return movieService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieReadDto findById(@PathVariable("id") Integer id) {
        return movieService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MovieReadDto create(@Validated @RequestBody MovieCreateEditDto movieCreateEditDto) {
        return movieService.create(movieCreateEditDto);
    }

    @PutMapping(value = "/{id}")
    public MovieReadDto update(@PathVariable("id") Integer id,
                               @Validated @RequestBody MovieCreateEditDto movieCreateEditDto) {
        return movieService.update(id, movieCreateEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        if (!movieService.delete(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
