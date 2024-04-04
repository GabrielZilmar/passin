package com.zilmar.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {
    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Success Events!");
    }
}
