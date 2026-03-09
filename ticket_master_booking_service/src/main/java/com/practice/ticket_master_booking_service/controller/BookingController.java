package com.practice.ticket_master_booking_service.controller;

import com.practice.ticket_master_booking_service.domain.Booking;
import com.practice.ticket_master_booking_service.model.request.SeatBookRequest;
import com.practice.ticket_master_booking_service.service.BookingService;
import com.practice.ticket_master_booking_service.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/seats")
public class BookingController {

    private final SeatService seatService;
    private final BookingService bookingService;

    @Autowired
    public BookingController(SeatService seatService,
                             BookingService bookingService) {
        this.seatService = seatService;
        this.bookingService = bookingService;
    }

    @GetMapping("/{eventId}")
    public Map<Object, Object> getSeats(@PathVariable Long eventId) {

        return seatService.getSeatMap(eventId);
    }

    @PostMapping("/book")
    public Booking bookSeats(@RequestBody SeatBookRequest request) {

        return bookingService.bookSeats(request);
    }

    @PostMapping("/reserve")
    public String reserveSeat(@RequestBody SeatBookRequest request) {

        boolean ok = seatService.reserveSeats(request.getVenueId(), request.getSection(), request.getSeatFields());

        if (!ok) {
            return "Seat not available";
        }

        return "Seat reserved";
    }
}
