package com.practice.ticket_master_search_service.controller;

import com.practice.ticket_master_search_service.dto.EventSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @GetMapping
    @RequestMapping("/search")
    public ResponseEntity<EventSearchResponse> getEvents() {
        try {
            LOGGER.info("Get event search response");
            return ResponseEntity.ok(new EventSearchResponse());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
