package com.practice.ticket_master_search_service.controller;

import com.practice.ticket_master_search_service.domain.EventDocument;
import com.practice.ticket_master_search_service.dto.EventSearchFilter;
import com.practice.ticket_master_search_service.dto.EventSearchResponse;
import com.practice.ticket_master_search_service.service.EventSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
public class EventSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventSearchController.class);

    private final EventSearchService eventSearchService;

    @Autowired
    public EventSearchController(EventSearchService eventSearchService) {
        this.eventSearchService = eventSearchService;
    }

    @GetMapping
    @RequestMapping("/search")
    public ResponseEntity<EventSearchResponse> getEvents(EventSearchFilter filter) {
        try {
            return ResponseEntity.ok(eventSearchService.getEventSearchResponse(filter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    @RequestMapping("/index")
    public ResponseEntity<String> indexDocument(@RequestBody EventDocument eventDocument) {
        try {
            eventSearchService.save(eventDocument);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
