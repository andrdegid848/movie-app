package com.example.application.service;

import com.example.application.database.repository.PersonRepository;
import com.example.application.dto.person.PersonCreateEditDto;
import com.example.application.dto.person.PersonReadDto;
import com.example.application.mapper.person.PersonCreateEditMapper;
import com.example.application.mapper.person.PersonReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonReadMapper personReadMapper;
    private final PersonCreateEditMapper personCreateEditMapper;

    public List<PersonReadDto> findAll() {
        return personRepository.findAll().stream()
                .map(personReadMapper::map)
                .toList();
    }

    public Optional<PersonReadDto> findById(Integer id) {
        return personRepository.findById(id)
                .map(personReadMapper::map);
    }

    @Transactional
    public PersonReadDto create(PersonCreateEditDto personCreateEditDto) {
        return Optional.of(personCreateEditDto)
                .map(personCreateEditMapper::map)
                .map(personRepository::save)
                .map(personReadMapper::map)
                .orElse(null);
    }

    @Transactional
    public Optional<PersonReadDto> update(Integer id, PersonCreateEditDto personCreateEditDto) {
        return personRepository.findById(id)
                .map(person -> personCreateEditMapper.map(personCreateEditDto, person))
                .map(personRepository::saveAndFlush)
                .map(personReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return personRepository.findById(id)
                .map(person -> {
                    personRepository.delete(person);
                    personRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
