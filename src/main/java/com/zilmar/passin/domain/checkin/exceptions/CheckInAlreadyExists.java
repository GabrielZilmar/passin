package com.zilmar.passin.domain.checkin.exceptions;

import com.zilmar.passin.config.CustomRunTimeException;
import org.springframework.http.HttpStatus;

public class CheckInAlreadyExists extends CustomRunTimeException {
    public CheckInAlreadyExists(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
