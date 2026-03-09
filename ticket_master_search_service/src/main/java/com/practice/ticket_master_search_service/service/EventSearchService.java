package com.practice.ticket_master_search_service.service;

import com.practice.ticket_master_search_service.domain.EventDocument;
import com.practice.ticket_master_search_service.dto.EventSearchFilter;
import com.practice.ticket_master_search_service.dto.EventSearchResponse;

import java.util.List;

public interface EventSearchService {

    EventSearchResponse getEventSearchResponse(EventSearchFilter filter);

    void save(EventDocument eventDocument);

    List<EventDocument> searchByName(String name);
}
