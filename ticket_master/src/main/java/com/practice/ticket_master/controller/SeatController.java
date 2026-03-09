package com.practice.ticket_master.controller;

import com.practice.ticket_master.dto.request.SeatCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;
import com.practice.ticket_master.service.SeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seat")
public class SeatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeatController.class);

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<ActionResponse> createSeat(@RequestBody SeatCreateRequest request) {
        try {
            return ResponseEntity.ok(seatService.create(request));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
