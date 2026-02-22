package com.practice.ticket_master_booking_service.service;

import com.practice.ticket_master_booking_service.domain.Booking;
import com.practice.ticket_master_booking_service.enumeration.BookingStatus;
import com.practice.ticket_master_booking_service.repository.BookingRepository;
import com.practice.ticket_master_booking_service.util.RedisUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final RedisUtil redisUtil;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, RedisUtil redisUtil) {
        this.bookingRepository = bookingRepository;
        this.redisUtil = redisUtil;
    }

    @Override
    @Transactional
    public Booking confirmBooking(Long userId,
                                  Long eventId,
                                  List<String> seats) {

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.CONFIRMED);

        booking = bookingRepository.save(booking);

        for (String seat : seats) {

            redisUtil.setSeatStatus(eventId, seat, "BOOKED");

            redisUtil.removeReservation(eventId, seat);
        }

        return booking;
    }
}
