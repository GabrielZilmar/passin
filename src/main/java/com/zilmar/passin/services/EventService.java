package com.zilmar.passin.services;

import com.zilmar.passin.domain.attendees.Attendee;
import com.zilmar.passin.domain.events.Event;
import com.zilmar.passin.dto.event.CreateEventRequestDto;
import com.zilmar.passin.dto.event.EventDetailsResponseDto;
import com.zilmar.passin.dto.event.EventIdDto;
import com.zilmar.passin.repositories.AttendeeRepository;
import com.zilmar.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventDetailsResponseDto getEventDetail(UUID eventId) {
        Event event = this.eventRepository
                .findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(event.getId());
        return new EventDetailsResponseDto(event, attendeeList.size());
    }

    private String createSlug(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        // Note: You can also use .replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "") to remove the accents
        return normalized
                .replaceAll("[^^a-zA-Z0-9\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }

    public EventIdDto createEvent(
        CreateEventRequestDto createEventRequestDto
    ) {
        Event newEvent = new Event();
        newEvent.setTitle(createEventRequestDto.title());
        newEvent.setDetails(createEventRequestDto.details());
        newEvent.setMaximumAttendees(createEventRequestDto.maximumAttendees());
        newEvent.setSlug(this.createSlug(createEventRequestDto.title()));

        this.eventRepository.save(newEvent);
        return new EventIdDto(newEvent.getId());
    }
}
