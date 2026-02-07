package com.practice.ticket_master.controller;

import com.practice.ticket_master.dto.EventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @GetMapping
    @RequestMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable Long eventId) {
        try {
            LOGGER.info("get event details");
            return ResponseEntity.ok(new EventDTO());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
