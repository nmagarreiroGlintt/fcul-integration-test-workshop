package com.glinttnext.fcul.workshop.controller;

import com.glinttnext.fcul.workshop.model.dto.PersonDto;
import com.glinttnext.fcul.workshop.model.dto.request.CreatePersonRequestDto;
import com.glinttnext.fcul.workshop.service.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
@RequestMapping(ControllerPaths.BASE + ControllerPaths.PERSON_OPERATIONS)
public class PersonController {
    
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = ControllerPaths.PERSON_ID)
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long personId) {
        PersonDto personDto = personService.getPersonById(personId);
        return ResponseEntity.ok().body(personDto);
    }

    @PostMapping(path = ControllerPaths.CREATE_PERSON)
    public ResponseEntity<PersonDto> createPerson(@RequestBody @NotNull @Valid CreatePersonRequestDto createPersonRequest) {
        return ResponseEntity.ok(personService.createPerson(createPersonRequest));
    }

}
