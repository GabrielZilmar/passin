package com.zilmar.passin.domain.attendees.exceptions;

import org.springframework.http.HttpStatus;

public class AttendeeAlreadyRegisteredException extends RuntimeException {
    public final HttpStatus status;

    public AttendeeAlreadyRegisteredException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
