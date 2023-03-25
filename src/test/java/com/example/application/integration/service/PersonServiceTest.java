package com.example.application.integration.service;

import com.example.application.database.entity.PersonRole;
import com.example.application.dto.person.PersonCreateEditDto;
import com.example.application.dto.person.PersonReadDto;
import com.example.application.integration.IntegrationTestBase;
import com.example.application.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class PersonServiceTest extends IntegrationTestBase {

    private final PersonService personService;
    private static final Integer PERSON_ID = 1;
    private static final Integer FAKE_PERSON_ID = -1;

    @Test
    void findAll() {
        List<PersonReadDto> people = personService.findAll();
        assertThat(people).hasSize(9);
    }

    @Test
    void findById() {
        Optional<PersonReadDto> person = personService.findById(PERSON_ID);
        assertTrue(person.isPresent());
        person.ifPresent(it -> assertEquals("Reeves", it.getLastname()));
    }

    @Test
    void update() {
        PersonCreateEditDto personCreateEditDto = new PersonCreateEditDto(
                "Test",
                "Test",
                LocalDate.of(2000, 1, 1),
                PersonRole.DIRECTOR
        );

        Optional<PersonReadDto> updatePerson = personService.update(PERSON_ID, personCreateEditDto);

        updatePerson.ifPresent(user -> {
            assertEquals(personCreateEditDto.getBirthDate(), user.getBirthDate());
            assertEquals(personCreateEditDto.getFirstname(), user.getFirstname());
            assertEquals(personCreateEditDto.getLastname(), user.getLastname());
            assertEquals(personCreateEditDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertFalse(personService.delete(FAKE_PERSON_ID));
    }
}