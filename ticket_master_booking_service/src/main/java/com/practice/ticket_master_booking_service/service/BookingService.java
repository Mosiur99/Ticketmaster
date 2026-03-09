package com.practice.ticket_master_booking_service.service;

import com.practice.ticket_master_booking_service.domain.Booking;
import com.practice.ticket_master_booking_service.model.request.SeatBookRequest;

import java.util.List;

public interface BookingService {

    Booking confirmBooking(Long userId,
                           Long eventId,
                           List<String> seats);

    Booking bookSeats(SeatBookRequest request);

    Booking createBookingAfterReservation(SeatBookRequest request);
}
