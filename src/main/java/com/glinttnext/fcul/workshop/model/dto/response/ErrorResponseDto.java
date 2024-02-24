package com.glinttnext.fcul.workshop.model.dto.response;

import java.io.Serializable;

public class ErrorResponseDto implements Serializable {

    private String messageCode;

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(final String messageCode) {
        this.messageCode = messageCode;
    }
}
