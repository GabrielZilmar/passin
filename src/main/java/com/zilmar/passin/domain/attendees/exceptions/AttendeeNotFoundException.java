package com.zilmar.passin.domain.attendees.exceptions;

import com.zilmar.passin.config.CustomRunTimeException;
import org.springframework.http.HttpStatus;

public class AttendeeNotFoundException extends CustomRunTimeException {
    public AttendeeNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
