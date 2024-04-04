package com.zilmar.passin.controllers;

import com.zilmar.passin.domain.attendees.Attendee;
import com.zilmar.passin.dto.event.EventDetailsResponseDto;
import com.zilmar.passin.services.AttendeeService;
import com.zilmar.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/attendees")
@ResponseBody
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping("/{eventId}")
    public ResponseEntity<List<Attendee>> getAllAttendeesFromEvent(@PathVariable UUID eventId) {
        List<Attendee> details = this.attendeeService.getAllAttendeesFromEvent(eventId);
        return ResponseEntity.ok(details);
    }
}
