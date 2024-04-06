package com.zilmar.passin.services;

import com.zilmar.passin.domain.attendees.Attendee;
import com.zilmar.passin.domain.checkin.CheckIn;
import com.zilmar.passin.domain.checkin.exceptions.CheckInAlreadyExists;
import com.zilmar.passin.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckInRepository checkInRepository;

    private void verifyCheckInExists(UUID attendeeId) {
        Optional<CheckIn> isCheckedIn = this.checkInRepository.findByAttendeeId(attendeeId);
        if(isCheckedIn.isPresent()) {
            throw new CheckInAlreadyExists("Attendee with id: "+ attendeeId + "is already checked in");
        }

    }

    public void registerCheckIn(Attendee attendee) {
        this.verifyCheckInExists(attendee.getId());
        CheckIn checkIn = new CheckIn();
        checkIn.setAttendee(attendee);
        this.checkInRepository.save(checkIn);
    }
}
