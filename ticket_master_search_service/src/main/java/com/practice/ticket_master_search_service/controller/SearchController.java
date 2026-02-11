package com.practice.ticket_master_search_service.controller;

import com.practice.ticket_master_search_service.dto.EventSearchFilter;
import com.practice.ticket_master_search_service.dto.EventSearchResponse;
import com.practice.ticket_master_search_service.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    @RequestMapping("/search")
    public ResponseEntity<EventSearchResponse> getEvents(EventSearchFilter filter) {
        try {
            return ResponseEntity.ok(searchService.getEventSearchResponse(filter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
