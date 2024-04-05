package com.zilmar.passin.domain.attendees.exceptions;

import org.springframework.http.HttpStatus;

public class AttendeeNotFoundException extends RuntimeException {
    public final HttpStatus status;

    public AttendeeNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }
}
