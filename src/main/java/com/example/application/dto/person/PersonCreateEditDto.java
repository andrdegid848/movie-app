package com.example.application.dto.person;

import com.example.application.database.entity.PersonRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.time.LocalDate;

@Value
public class PersonCreateEditDto {

    @NotBlank
    @Size(min = 3, max = 64)
    String firstname;

    @NotBlank
    @Size(min = 3, max = 64)
    String lastname;

    @Past
    LocalDate birthDate;

    PersonRole role;
}
