package com.practice.ticket_master_booking_service.controller;

import com.practice.ticket_master_booking_service.domain.Booking;
import com.practice.ticket_master_booking_service.model.request.PaymentRequest;
import com.practice.ticket_master_booking_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Booking> pay(@RequestBody PaymentRequest request) {

        Booking booking = paymentService.payForBooking(request);

        return ResponseEntity.ok(booking);
    }
}

