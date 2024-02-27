package com.glinttnext.fcul.workshop.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.glinttnext.fcul.workshop.controller.util.ControllerConstants;
import com.glinttnext.fcul.workshop.data.entity.Person;
import com.glinttnext.fcul.workshop.data.repository.PersonRepository;
import com.glinttnext.fcul.workshop.model.dto.PersonDto;
import com.glinttnext.fcul.workshop.model.dto.request.CreatePersonRequestDto;
import com.glinttnext.fcul.workshop.model.dto.response.ErrorResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

public class PersonControllerTest extends IntegrationParentTest {

    @Autowired
    PersonRepository personRepository;

    @AfterEach
    void clean() {
        this.personRepository.deleteAll();
        this.personRepository.flush();
    }

    @Test
    public void testGetPersonById_IdInvalid() throws Exception {
        final var personId = "abc";

        final var endpoint = ControllerPaths.BASE + ControllerPaths.PERSON_OPERATIONS + "/" + personId;

        final MockHttpServletResponse response = this.mockMvcPerformGet(endpoint);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void testGetPersonById_IdValidNotFound() throws Exception {
        final var personId = "12345";

        final var endpoint = ControllerPaths.BASE + ControllerPaths.PERSON_OPERATIONS + "/" + personId;

        final MockHttpServletResponse response = this.mockMvcPerformGet(endpoint);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

        final ErrorResponseDto responseObject = this.getResponseObjectFromResponse(response,
                new TypeReference<ErrorResponseDto>() {
                });
        Assertions.assertNotNull(responseObject);
        Assertions.assertEquals(ControllerConstants.MESSAGE_NOT_FOUND_ERROR, responseObject.getMessageCode());
    }

    @Test
    public void testGetPersonById_Found() throws Exception {
        var name = "Ana";
        var persistedPerson = personRepository.save(new Person(name));

        final var personId = persistedPerson.getId();
        final var endpoint = ControllerPaths.BASE + ControllerPaths.PERSON_OPERATIONS + "/" + personId;

        final MockHttpServletResponse response = this.mockMvcPerformGet(endpoint);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        final PersonDto responseObject = this.getResponseObjectFromResponse(response,
                new TypeReference<PersonDto>() {
                });
        Assertions.assertNotNull(responseObject);
        Assertions.assertEquals(personId, responseObject.getId());
        Assertions.assertEquals(name, responseObject.getName());
    }

    @Test
    public void testCreatePerson_NullBody() throws Exception {
        final var endpoint = ControllerPaths.BASE + ControllerPaths.PERSON_OPERATIONS + ControllerPaths.CREATE_PERSON;

        Object request = null;
        final MockHttpServletResponse response = this.mockMvcPerformPost(endpoint, request);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void testCreatePerson_NameBlank() throws Exception {
        final var endpoint = ControllerPaths.BASE + ControllerPaths.PERSON_OPERATIONS + ControllerPaths.CREATE_PERSON;

        var request = new CreatePersonRequestDto();
        request.setName("");

        final MockHttpServletResponse response = this.mockMvcPerformPost(endpoint, request);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void testCreatePerson_Success() throws Exception {
        //TODO implement
    }
}
