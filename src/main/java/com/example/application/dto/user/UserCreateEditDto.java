package com.example.application.dto.user;

import com.example.application.database.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.postgresql.util.LruCache.CreateAction;

@Value
public class UserCreateEditDto {

    @Email
    String username;

    @NotBlank(groups = CreateAction.class)
    String rawPassword;

    @NotBlank
    @Size(min = 3, max = 64)
    String firstname;

    @NotBlank
    @Size(min = 3, max = 64)
    String lastname;

    Role role;
}
