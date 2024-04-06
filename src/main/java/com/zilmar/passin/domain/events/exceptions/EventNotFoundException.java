package com.zilmar.passin.domain.events.exceptions;

import com.zilmar.passin.config.CustomRunTimeException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EventNotFoundException extends CustomRunTimeException {
    public EventNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
