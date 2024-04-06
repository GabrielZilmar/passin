package com.zilmar.passin.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomRunTimeException extends RuntimeException {
    public final HttpStatus status;

    public CustomRunTimeException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CustomRunTimeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
