package com.practice.ticket_master.controller;

import com.practice.ticket_master.dto.request.PerformerCreateRequest;
import com.practice.ticket_master.dto.request.VenueCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;
import com.practice.ticket_master.service.PerformerService;
import com.practice.ticket_master.service.VenueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/performer")
public class PerformerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VenueController.class);

    private final PerformerService performerService;

    @Autowired
    public PerformerController(PerformerService performerService) {
        this.performerService = performerService;
    }

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<ActionResponse> performerCreate(@RequestBody PerformerCreateRequest request) {
        try {
            return ResponseEntity.ok(performerService.create(request));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
