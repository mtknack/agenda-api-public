package com.mtknack.agenda.Exceptions.DTOs;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final String details;

    public CustomException(String message, HttpStatus status, String details) {
        super(message);
        this.status = status;
        this.details = details;
    }

    public HttpStatus getStatus() { return status; }
    public String getDetails() { return details; }
}
