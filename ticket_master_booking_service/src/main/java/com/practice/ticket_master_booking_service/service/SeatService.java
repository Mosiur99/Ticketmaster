package com.practice.ticket_master_booking_service.service;

import java.util.Map;

public interface SeatService {

    Map<Object, Object> getSeatMap(Long eventId);

    boolean reserveSeat(Long eventId, String seat);
}
