package com.zilmar.passin.domain.attendees.exceptions;

import com.zilmar.passin.config.CustomRunTimeException;
import org.springframework.http.HttpStatus;

public class AttendeeAlreadyRegisteredException extends CustomRunTimeException {

    public AttendeeAlreadyRegisteredException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
