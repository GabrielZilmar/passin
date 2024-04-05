package com.zilmar.passin.services;

import com.zilmar.passin.domain.attendees.Attendee;
import com.zilmar.passin.domain.attendees.exceptions.AttendeeAlreadyRegisteredException;
import com.zilmar.passin.dto.attendee.AttendeeDetails;
import com.zilmar.passin.dto.attendee.AttendeeListResponseDto;
import com.zilmar.passin.repositories.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public List<Attendee> getAllAttendeesFromEvent(UUID eventId) {
        return this.attendeeRepository.findAllByEventId(eventId);
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
}
