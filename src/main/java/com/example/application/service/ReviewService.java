package com.example.application.service;

import com.example.application.database.repository.ReviewRepository;
import com.example.application.dto.review.ReviewCreateEditDto;
import com.example.application.dto.review.ReviewReadDto;
import com.example.application.mapper.review.ReviewCreateEditMapper;
import com.example.application.mapper.review.ReviewReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewReadMapper reviewReadMapper;
    private final ReviewCreateEditMapper reviewCreateEditMapper;

    public List<ReviewReadDto> findAll() {
        return reviewRepository.findAll().stream()
                .map(reviewReadMapper::map)
                .toList();
    }

    public Optional<ReviewReadDto> findById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewReadMapper::map);
    }

    public List<ReviewReadDto> findAllByMovieId(Integer id) {
        return reviewRepository.findAllByMovieId(id).stream()
                .map(reviewReadMapper::map)
                .toList();
    }

    @Transactional
    public ReviewReadDto create(ReviewCreateEditDto reviewCreateEditDto) {
        return Optional.of(reviewCreateEditDto)
                .map(reviewCreateEditMapper::map)
                .map(reviewRepository::save)
                .map(reviewReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ReviewReadDto> update(Long id, ReviewCreateEditDto reviewCreateEditDto) {
        return reviewRepository.findById(id)
                .map(review -> reviewCreateEditMapper.map(reviewCreateEditDto, review))
                .map(reviewRepository::saveAndFlush)
                .map(reviewReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return reviewRepository.findById(id)
                .map(review -> {
                    reviewRepository.delete(review);
                    reviewRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
