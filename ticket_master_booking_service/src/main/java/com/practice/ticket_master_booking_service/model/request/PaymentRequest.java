package com.practice.ticket_master_booking_service.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    private Long bookingId;
    private Double amount;
}

