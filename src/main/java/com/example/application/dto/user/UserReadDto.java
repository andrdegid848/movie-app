package com.example.application.dto.user;

import com.example.application.database.entity.Role;
import lombok.Value;

@Value
public class UserReadDto {
    Long id;
    String username;
    String firstname;
    String lastname;
    Role role;
}
