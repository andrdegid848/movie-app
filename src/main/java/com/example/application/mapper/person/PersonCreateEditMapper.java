package com.example.application.mapper.person;

import com.example.application.database.entity.Person;
import com.example.application.dto.person.PersonCreateEditDto;
import com.example.application.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PersonCreateEditMapper implements Mapper<PersonCreateEditDto, Person> {

    @Override
    public Person map(PersonCreateEditDto object) {
        Person person = new Person();
        copy(object, person);
        return person;
    }

    @Override
    public Person map(PersonCreateEditDto fromObject, Person toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(PersonCreateEditDto personCreateEditDto, Person person) {
        person.setFirstname(personCreateEditDto.getFirstname());
        person.setLastname(personCreateEditDto.getLastname());
        person.setBirthDate(personCreateEditDto.getBirthDate());
        person.setRole(personCreateEditDto.getRole());
    }
}

