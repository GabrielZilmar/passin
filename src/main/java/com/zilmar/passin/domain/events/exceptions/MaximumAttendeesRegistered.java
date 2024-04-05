package com.zilmar.passin.domain.events.exceptions;

import org.springframework.http.HttpStatus;

public class MaximumAttendeesRegistered extends RuntimeException {
    public final HttpStatus status;

    public MaximumAttendeesRegistered(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
