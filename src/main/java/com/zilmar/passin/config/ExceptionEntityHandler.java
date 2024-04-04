package com.zilmar.passin.config;

import com.zilmar.passin.config.dto.ExceptionBodyDto;
import com.zilmar.passin.domain.events.exceptions.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handleBadRequestException(EventNotFoundException ex) {
        return new ResponseEntity(new ExceptionBodyDto(ex.getMessage()), ex.getStatus());
    }
}
