package com.example.application.service;

import com.example.application.database.repository.MovieRepository;
import com.example.application.dto.movie.MovieCreateEditDto;
import com.example.application.dto.movie.MovieReadDto;
import com.example.application.mapper.movie.MovieCreateEditMapper;
import com.example.application.mapper.movie.MovieReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieReadMapper movieReadMapper;
    private final MovieCreateEditMapper movieCreateEditMapper;

    public List<MovieReadDto> findAll() {
        return movieRepository.findAll().stream()
                .map(movieReadMapper::map)
                .toList();
    }

    public Optional<MovieReadDto> findById(Integer id) {
        return movieRepository.findById(id)
                .map(movieReadMapper::map);
    }

    @Transactional
    public MovieReadDto create(MovieCreateEditDto movieCreateEditDto) {
        return Optional.of(movieCreateEditDto)
                .map(movieCreateEditMapper::map)
                .map((movieRepository::save))
                .map(movieReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<MovieReadDto> update(Integer id, MovieCreateEditDto movieCreateEditDto) {
        return movieRepository.findById(id)
                .map(movie -> movieCreateEditMapper.map(movieCreateEditDto, movie))
                .map(movieRepository::saveAndFlush)
                .map(movieReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movieRepository.delete(movie);
                    movieRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
