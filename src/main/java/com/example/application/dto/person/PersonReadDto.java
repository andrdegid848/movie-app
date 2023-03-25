package com.example.application.dto.person;

import com.example.application.database.entity.PersonRole;
import lombok.Value;

import java.time.LocalDate;

@Value
public class PersonReadDto {
    Integer id;
    String firstname;
    String lastname;
    LocalDate birthDate;
    PersonRole role;
}
