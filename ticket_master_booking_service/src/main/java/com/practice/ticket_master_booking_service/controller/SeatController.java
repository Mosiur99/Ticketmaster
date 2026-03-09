package com.practice.ticket_master_booking_service.controller;

import com.practice.ticket_master_booking_service.model.Response.ActionResponse;
import com.practice.ticket_master_booking_service.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seat")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping
    @RequestMapping("/set-in-redis")
    public ResponseEntity<ActionResponse> seatSeatMapInRedis(@RequestParam Long venueId) {
        try {
            return ResponseEntity.ok(seatService.setSeatMapInRedis(venueId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
