package com.example.application.dto.review;

import com.example.application.dto.movie.MovieReadDto;
import com.example.application.dto.user.UserReadDto;
import lombok.Value;

@Value
public class ReviewReadDto {
    Long id;
    MovieReadDto movie;
    UserReadDto user;
    String text;
    Integer filmScore;
}
