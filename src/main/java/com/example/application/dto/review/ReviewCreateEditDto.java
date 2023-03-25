package com.example.application.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ReviewCreateEditDto {

    @NotNull
    Integer movieId;

    @NotNull
    Long userId;

    @NotBlank
    String text;

    @NotNull
    Integer filmScore;
}
