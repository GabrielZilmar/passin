package com.zilmar.passin.dto.attendee;

import java.time.LocalDateTime;
import java.util.UUID;

public record AttendeeDetails(
        UUID id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime checkedInAt
) {
}
