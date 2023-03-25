package com.example.application.integration.service;

import com.example.application.database.entity.Role;
import com.example.application.dto.user.UserCreateEditDto;
import com.example.application.dto.user.UserReadDto;
import com.example.application.integration.IntegrationTestBase;
import com.example.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class UserServiceTest extends IntegrationTestBase {

    private static final Long USER_ID = 1L;
    public static final Long FAKE_USER_ID = -1L;
    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> users = userService.findAll();
        assertThat(users).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> user = userService.findById(USER_ID);
        assertTrue(user.isPresent());
        user.ifPresent(it -> assertEquals("misha@gmail.com", it.getUsername()));
    }

    @Test
    void update() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "test@gmail.com",
                "111",
                "Test",
                "Test",
                Role.USER
        );

        Optional<UserReadDto> updateUser = userService.update(USER_ID, userCreateEditDto);

        updateUser.ifPresent(user -> {
            assertEquals(userCreateEditDto.getUsername(), user.getUsername());
            assertEquals(userCreateEditDto.getFirstname(), user.getFirstname());
            assertEquals(userCreateEditDto.getLastname(), user.getLastname());
            assertEquals(userCreateEditDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.delete(FAKE_USER_ID));
    }
}