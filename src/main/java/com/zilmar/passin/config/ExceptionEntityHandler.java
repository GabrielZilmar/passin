package com.zilmar.passin.config;

import com.zilmar.passin.config.dto.ExceptionBodyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(CustomRunTimeException.class)
    public ResponseEntity<String> handleBadRequestException(CustomRunTimeException ex) {
        return new ResponseEntity(new ExceptionBodyDto(ex.getMessage()), ex.getStatus());
    }
}
