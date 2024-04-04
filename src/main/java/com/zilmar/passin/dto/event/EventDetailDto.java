package com.zilmar.passin.dto.event;

import java.util.UUID;

public record EventDetailDto(
        UUID id,
        String title,
        String details,
        String slug,
        Integer maximumAttendees,
        Integer attendeesAmount
) {}
