package com.zilmar.passin.repositories;

import com.zilmar.passin.domain.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
    public Optional<CheckIn> findByAttendeeId(UUID attendeeId);
}
