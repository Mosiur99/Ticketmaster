package com.practice.ticket_master_booking_service.service;

import com.practice.ticket_master_booking_service.domain.Booking;

import java.util.List;

public interface BookingService {

    Booking confirmBooking(Long userId,
                           Long eventId,
                           List<String> seats);
}
