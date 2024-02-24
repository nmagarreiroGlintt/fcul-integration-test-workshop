package com.glinttnext.fcul.workshop.model.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class CreatePersonRequestDto implements Serializable {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
