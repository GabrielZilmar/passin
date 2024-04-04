package com.zilmar.passin.dto.event;

import com.zilmar.passin.domain.events.Event;
import lombok.Getter;

@Getter
public class EventDetailsResponseDto {
    EventDetailDto eventDetailDto;

    public EventDetailsResponseDto(
            Event event,
            Integer numberOfAttendees
    ) {
        this.eventDetailDto = new EventDetailDto(
                event.getId(),
                event.getTitle(),
                event.getDetails(),
                event.getSlug(),
                event.getMaximumAttendees(),
                numberOfAttendees
        );
    }
}
