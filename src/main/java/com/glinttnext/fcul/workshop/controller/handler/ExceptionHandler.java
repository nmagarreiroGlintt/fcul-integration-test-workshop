package com.glinttnext.fcul.workshop.controller.handler;

import com.glinttnext.fcul.workshop.controller.util.ControllerConstants;
import com.glinttnext.fcul.workshop.model.dto.response.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.invoke.MethodHandles;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        LOGGER.warn(
                "ExceptionHandler - NoSuchElementException - Message: {}.",
                ex.getMessage());
        LOGGER.debug("Stacktrace: ", ex);

        var responseBody = new ErrorResponseDto();
        responseBody.setMessageCode(ControllerConstants.MESSAGE_NOT_FOUND_ERROR);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        LOGGER.warn(
                "ExceptionHandler - Exception - Message: {}.",
                ex.getMessage());
        LOGGER.debug("Stacktrace: ", ex);

        var responseBody = new ErrorResponseDto();
        responseBody.setMessageCode(ControllerConstants.MESSAGE_GENERIC_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}