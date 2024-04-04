package com.zilmar.passin.dto.event;

public record CreateEventRequestDto(
        String title,
        String details,
        Integer maximumAttendees
) {
}
