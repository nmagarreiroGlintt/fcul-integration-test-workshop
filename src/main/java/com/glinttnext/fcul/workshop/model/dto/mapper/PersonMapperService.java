package com.glinttnext.fcul.workshop.model.dto.mapper;

import com.glinttnext.fcul.workshop.data.entity.Person;
import com.glinttnext.fcul.workshop.model.dto.PersonDto;
import com.glinttnext.fcul.workshop.model.dto.request.CreatePersonRequestDto;
import org.springframework.stereotype.Service;

@Service
public class PersonMapperService {

    public PersonDto mapPersonToPersonDto(Person person) {
        if (person == null) {
            return null;
        }

        var personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setName(person.getName());
        return personDto;
    }

    public Person mapCreatePersonRequestDtoToPerson(CreatePersonRequestDto createPersonRequest) {
        if (createPersonRequest == null) {
            return null;
        }

        var person = new Person();
        person.setName(createPersonRequest.getName());
        return person;
    }
}

