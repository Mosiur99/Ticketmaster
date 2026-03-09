package com.practice.ticket_master_booking_service.service;

import com.practice.ticket_master_booking_service.domain.Booking;
import com.practice.ticket_master_booking_service.model.request.PaymentRequest;

public interface PaymentService {

    Booking payForBooking(PaymentRequest request);
}

