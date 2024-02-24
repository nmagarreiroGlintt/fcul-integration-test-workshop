package com.glinttnext.fcul.workshop.service;

import com.glinttnext.fcul.workshop.data.entity.Person;
import com.glinttnext.fcul.workshop.data.repository.PersonRepository;
import com.glinttnext.fcul.workshop.model.dto.PersonDto;
import com.glinttnext.fcul.workshop.model.dto.mapper.PersonMapperService;
import com.glinttnext.fcul.workshop.model.dto.request.CreatePersonRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapperService personMapperService;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapperService personMapperService) {
        this.personRepository = personRepository;
        this.personMapperService = personMapperService;
    }

    public PersonDto getPersonById(final Long id) {
        Optional<Person> person = this.personRepository.findById(id);
        return personMapperService.mapPersonToPersonDto(person.orElseThrow());
    }

    public PersonDto createPerson(CreatePersonRequestDto createPersonRequest) {
        Person person = this.personRepository.save(personMapperService.mapCreatePersonRequestDtoToPerson(createPersonRequest));
        person.setCreatedDate(LocalDate.now().minusYears(1));
        return personMapperService.mapPersonToPersonDto(person);
    }
}
