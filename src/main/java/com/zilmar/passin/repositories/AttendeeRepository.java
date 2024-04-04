package com.zilmar.passin.repositories;

import com.zilmar.passin.domain.attendees.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AttendeeRepository extends JpaRepository<Attendee, UUID> {
    public List<Attendee> findByEventId(UUID eventId);
}
