package com.zilmar.passin.dto.attendee;

import java.util.UUID;

public record AttendeeBadgeDto(
        String name,
        String email,
        String checkInUrl,
        UUID eventId
) {}
