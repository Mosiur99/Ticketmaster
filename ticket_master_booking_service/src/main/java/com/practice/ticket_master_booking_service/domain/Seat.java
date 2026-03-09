package com.practice.ticket_master_booking_service.domain;

import com.practice.ticket_master_booking_service.enumeration.SeatSection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "row_name", nullable = false)
    private String rowName;

    @Enumerated(EnumType.STRING)
    @Column(name = "section", nullable = false)
    private SeatSection section;
}
