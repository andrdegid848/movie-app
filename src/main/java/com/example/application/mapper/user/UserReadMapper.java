package com.example.application.mapper.user;

import com.example.application.database.entity.User;
import com.example.application.dto.user.UserReadDto;
import com.example.application.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getFirstname(),
                object.getLastname(),
                object.getRole()
        );
    }
}
