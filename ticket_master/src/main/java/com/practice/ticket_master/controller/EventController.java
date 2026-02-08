package com.practice.ticket_master.controller;

import com.practice.ticket_master.dto.EventDTO;
import com.practice.ticket_master.dto.request.EventCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;
import com.practice.ticket_master.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

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

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<ActionResponse> createEvent(@RequestBody EventCreateRequest request) {
        try {
            return ResponseEntity.ok(eventService.create(request));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
