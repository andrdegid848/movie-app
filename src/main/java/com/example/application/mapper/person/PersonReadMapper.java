package com.example.application.mapper.person;

import com.example.application.database.entity.Person;
import com.example.application.dto.person.PersonReadDto;
import com.example.application.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PersonReadMapper implements Mapper<Person, PersonReadDto> {

    @Override
    public PersonReadDto map(Person object) {
        return new PersonReadDto(
                object.getId(),
                object.getFirstname(),
                object.getLastname(),
                object.getBirthDate(),
                object.getRole()
        );
    }
}
