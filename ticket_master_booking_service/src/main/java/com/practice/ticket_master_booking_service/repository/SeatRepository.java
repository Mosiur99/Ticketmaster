package com.practice.ticket_master_booking_service.repository;

import com.practice.ticket_master_booking_service.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> getSeatsByVenueId(Long venueId);
}
