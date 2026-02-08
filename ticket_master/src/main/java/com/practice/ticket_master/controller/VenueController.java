package com.practice.ticket_master.controller;

import com.practice.ticket_master.dto.request.VenueCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;
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
@RequestMapping("/api/v1/venue")
public class VenueController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VenueController.class);

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<ActionResponse> venueCreate(@RequestBody VenueCreateRequest request) {
        try {
            return ResponseEntity.ok(venueService.create(request));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
