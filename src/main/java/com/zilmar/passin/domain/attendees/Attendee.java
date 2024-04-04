package com.zilmar.passin.domain.attendees;

import com.zilmar.passin.domain.checkin.CheckIn;
import com.zilmar.passin.domain.events.Event;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "attendees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false, unique = true)
    private Event event;

    @OneToOne(mappedBy = "attendee")
    private CheckIn checkIn;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}
