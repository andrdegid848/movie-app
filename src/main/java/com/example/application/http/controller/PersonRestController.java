package com.example.application.http.controller;

import com.example.application.dto.person.PersonCreateEditDto;
import com.example.application.dto.person.PersonReadDto;
import com.example.application.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
public class PersonRestController {

    private final PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonReadDto> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonReadDto findById(@PathVariable("id") Integer id) {
        return personService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PersonReadDto create(@Validated @RequestBody PersonCreateEditDto personCreateEditDto) {
        return personService.create(personCreateEditDto);
    }

    @PutMapping(value = "/{id}")
    public PersonReadDto update(@PathVariable("id") Integer id,
                                @Validated @RequestBody PersonCreateEditDto personCreateEditDto) {
        return personService.update(id, personCreateEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        if (!personService.delete(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
