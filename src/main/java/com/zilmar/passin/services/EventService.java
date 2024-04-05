package com.zilmar.passin.services;

import com.zilmar.passin.domain.attendees.Attendee;
import com.zilmar.passin.domain.events.Event;
import com.zilmar.passin.domain.events.exceptions.EventNotFoundException;
import com.zilmar.passin.domain.events.exceptions.MaximumAttendeesRegistered;
import com.zilmar.passin.dto.attendee.AttendeeIdDto;
import com.zilmar.passin.dto.attendee.AttendeeRequestDto;
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
    private final AttendeeService attendeeService;

    private Event findByIdOrThrow(UUID eventId) {
        return this.eventRepository
                .findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
    }

    public EventDetailsResponseDto getEventDetail(UUID eventId) {
        Event event = this.findByIdOrThrow(eventId);
        List<Attendee> attendeeList = this.attendeeRepository.findAllByEventId(event.getId());
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

    public AttendeeIdDto registerAttendeeOnEvent(UUID eventId, AttendeeRequestDto attendeeRequestDto) {
        this.attendeeService.verifyAttendeeSubscription(attendeeRequestDto.email(), eventId);
        Event event = this.findByIdOrThrow(eventId);

        List<Attendee> attendeeList = this.attendeeRepository.findAllWithCheckInByEventId(eventId);
        if(attendeeList.size() >= event.getMaximumAttendees()) {
            throw new MaximumAttendeesRegistered("The event: " + event.getTitle() + " is already full");
        }

        Attendee newAttendee = new Attendee();
        newAttendee.setName(attendeeRequestDto.name());
        newAttendee.setEmail(attendeeRequestDto.email());
        newAttendee.setEvent(event);

        this.attendeeService.registerAttendee(newAttendee);
        return new AttendeeIdDto(newAttendee.getId());
    }
}
