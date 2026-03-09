package com.practice.ticket_master_booking_service.repository;

import com.practice.ticket_master_booking_service.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
