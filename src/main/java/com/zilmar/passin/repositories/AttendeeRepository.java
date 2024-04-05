package com.zilmar.passin.repositories;

import com.zilmar.passin.domain.attendees.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendeeRepository extends JpaRepository<Attendee, UUID> {
    public List<Attendee> findAllByEventId(UUID eventId);

    @Query(value =
            "SELECT attendees.* FROM attendees " +
            "LEFT JOIN check_ins ON attendees.id = check_ins.attendee_id " +
            "WHERE attendees.event_id = ?1", nativeQuery = true)
    public List<Attendee> findAllWithCheckInByEventId(UUID eventId);

    public Optional<Attendee> findByEventIdAndEmail(UUID eventId, String email);
}
