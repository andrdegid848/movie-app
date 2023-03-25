package com.example.application.database.repository;

import com.example.application.database.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.movie.id = :id")
    List<Review> findAllByMovieId(Integer id);
}
