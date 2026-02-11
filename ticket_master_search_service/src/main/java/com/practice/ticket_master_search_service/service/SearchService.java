package com.practice.ticket_master_search_service.service;

import com.practice.ticket_master_search_service.dto.EventSearchFilter;
import com.practice.ticket_master_search_service.dto.EventSearchResponse;

public interface SearchService {

    EventSearchResponse getEventSearchResponse(EventSearchFilter filter);
}
