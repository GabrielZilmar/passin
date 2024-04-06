package com.zilmar.passin.controllers;

import com.zilmar.passin.domain.attendees.Attendee;
import com.zilmar.passin.dto.attendee.AttendeeBadgeResponseDto;
import com.zilmar.passin.dto.event.EventDetailsResponseDto;
import com.zilmar.passin.dto.event.EventIdDto;
import com.zilmar.passin.services.AttendeeService;
import com.zilmar.passin.services.CheckInService;
import com.zilmar.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/attendees")
@ResponseBody
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;
    private final CheckInService checkInService;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeResponseDto> getAttendeeBadge(
            @PathVariable UUID attendeeId,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        AttendeeBadgeResponseDto response = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);
        return ResponseEntity.ok(response);
    }

    @PostMapping("{attendeeId}/check-in")
    public ResponseEntity<EventIdDto> checkInAttendee(
            @PathVariable UUID attendeeId,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        this.attendeeService.checkInAttendee(attendeeId);

        var uri = uriComponentsBuilder
                .path("/attendees/{attendeeId}/badge")
                .buildAndExpand(attendeeId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
