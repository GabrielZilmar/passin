package com.zilmar.passin.controllers;

import com.zilmar.passin.dto.attendee.AttendeeListResponseDto;
import com.zilmar.passin.dto.event.CreateEventRequestDto;
import com.zilmar.passin.dto.event.EventDetailsResponseDto;
import com.zilmar.passin.dto.event.EventIdDto;
import com.zilmar.passin.services.AttendeeService;
import com.zilmar.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/events")
@ResponseBody
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDetailsResponseDto> getEventDetails(@PathVariable UUID eventId) {
        EventDetailsResponseDto details = this.eventService.getEventDetail(eventId);
        return ResponseEntity.ok(details);
    }

    @PostMapping()
    public ResponseEntity<EventIdDto> createEvent(
            @RequestBody CreateEventRequestDto body,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        EventIdDto eventIdDto = this.eventService.createEvent(body);

        var uri = uriComponentsBuilder
                .path("/events/{eventId}")
                .buildAndExpand(eventIdDto.id())
                .toUri();

        return ResponseEntity.created(uri).body(eventIdDto);
    }

    @GetMapping("/attendees/{eventId}")
    public ResponseEntity<AttendeeListResponseDto> getEventAttendees(@PathVariable UUID eventId) {
        AttendeeListResponseDto attendees = this.attendeeService.getEventsAttendee(eventId);
        return ResponseEntity.ok(attendees);
    }
}
