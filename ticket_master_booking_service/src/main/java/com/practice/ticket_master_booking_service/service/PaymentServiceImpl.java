package com.practice.ticket_master_booking_service.service;

import com.practice.ticket_master_booking_service.domain.Booking;
import com.practice.ticket_master_booking_service.enumeration.BookingStatus;
import com.practice.ticket_master_booking_service.model.request.PaymentRequest;
import com.practice.ticket_master_booking_service.repository.BookingRepository;
import com.practice.ticket_master_booking_service.util.RedisUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final BookingRepository bookingRepository;
    private final RedisUtil redisUtil;

    @Autowired
    public PaymentServiceImpl(BookingRepository bookingRepository,
                              RedisUtil redisUtil) {
        this.bookingRepository = bookingRepository;
        this.redisUtil = redisUtil;
    }

    @Override
    @Transactional
    public Booking payForBooking(PaymentRequest request) {

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getBookingStatus() != BookingStatus.PENDING) {
            throw new RuntimeException("Booking is not pending payment");
        }

        Map<String, String> reservationData = redisUtil.getReservationData(request.getBookingId());

        if (reservationData.isEmpty()) {
            throw new RuntimeException("Reservation expired or not found");
        }

        Long venueId = Long.valueOf(reservationData.get("venueId"));
        String section = reservationData.get("section");
        String seatsCsv = reservationData.get("seats");
        List<String> seats = Arrays.asList(seatsCsv.split(","));

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new RuntimeException("Invalid payment amount");
        }

        String key = "seat:venue:" + venueId + ":section:" + section;
        redisUtil.bookSeats(key, seats);

        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setTotalPrice(request.getAmount());

        bookingRepository.save(booking);

        redisUtil.clearReservation(request.getBookingId());

        return booking;
    }
}

