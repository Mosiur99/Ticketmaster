package com.practice.ticket_master_booking_service.model.request;

import com.practice.ticket_master_booking_service.enumeration.SeatSection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeatBookRequest {

    private Long venueId;
    private SeatSection section;
    private List<String> seatFields;
}
