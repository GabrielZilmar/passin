package com.zilmar.passin.services;

import com.zilmar.passin.domain.attendees.Attendee;
import com.zilmar.passin.domain.attendees.exceptions.AttendeeAlreadyRegisteredException;
import com.zilmar.passin.domain.attendees.exceptions.AttendeeNotFoundException;
import com.zilmar.passin.dto.attendee.AttendeeBadgeResponseDto;
import com.zilmar.passin.dto.attendee.AttendeeDetails;
import com.zilmar.passin.dto.attendee.AttendeeListResponseDto;
import com.zilmar.passin.dto.attendee.AttendeeBadgeDto;
import com.zilmar.passin.repositories.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckInService checkInService;

    private Attendee getAttendee(UUID attendeeId) {
        Attendee attendee = this.attendeeRepository
                .findById(attendeeId)
                .orElseThrow(() -> new AttendeeNotFoundException("Not found attendee  with id: " + attendeeId));

        return attendee;
    }

    public AttendeeListResponseDto getEventsAttendee(UUID eventId) {
        List<Attendee> attendeeList = this.attendeeRepository.findAllWithCheckInByEventId(eventId);
        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> new AttendeeDetails(
                attendee.getId(),
                attendee.getName(),
                attendee.getEmail(),
                attendee.getCreatedAt(),
                (attendee.getCheckIn() != null) ? attendee.getCheckIn().getCreatedAt() : null
        )).toList();

        return new AttendeeListResponseDto(attendeeDetailsList);
    }

    public Attendee registerAttendee(Attendee newAttendee) {
        return this.attendeeRepository.save(newAttendee);
    }

    public void verifyAttendeeSubscription(String email, UUID eventId) {
        Optional<Attendee> isAttendeeRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId, email);
        if(isAttendeeRegistered.isPresent()) {
            throw new AttendeeAlreadyRegisteredException("Attendee is already registered");
        }
    }

    public AttendeeBadgeResponseDto getAttendeeBadge(UUID attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        Attendee attendee = this.getAttendee(attendeeId);
        var uri = uriComponentsBuilder
                .path("/attendees/{attendeeId}/check-in")
                .buildAndExpand(attendeeId)
                .toUri()
                .toString();

        return new AttendeeBadgeResponseDto(new AttendeeBadgeDto(
                attendee.getName(),
                attendee.getEmail(),
                uri,
                attendee.getEvent().getId()
        ));
    }

    public void checkInAttendee(UUID attendeeId) {
        Attendee attendee = this.getAttendee(attendeeId);
        this.checkInService.registerCheckIn(attendee);
    }
}
