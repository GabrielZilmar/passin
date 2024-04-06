package com.zilmar.passin.domain.events.exceptions;

import com.zilmar.passin.config.CustomRunTimeException;
import org.springframework.http.HttpStatus;

public class MaximumAttendeesRegistered extends CustomRunTimeException {
    public MaximumAttendeesRegistered(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
