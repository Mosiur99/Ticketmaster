package com.practice.ticket_master_booking_service.service;

import com.practice.ticket_master_booking_service.enumeration.SeatSection;
import com.practice.ticket_master_booking_service.model.Response.ActionResponse;

import java.util.List;
import java.util.Map;

public interface SeatService {

    Map<Object, Object> getSeatMap(Long eventId);

    ActionResponse setSeatMapInRedis(Long venueId);

    boolean reserveSeats(Long venueId, SeatSection section, List<String> seatFields);
}
