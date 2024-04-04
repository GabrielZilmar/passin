package com.zilmar.passin.domain.events.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EventNotFoundException extends RuntimeException {
    public final HttpStatus status;

    public EventNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }
}
