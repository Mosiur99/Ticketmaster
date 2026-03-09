package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Event;
import com.practice.ticket_master.dto.EventSearchFilter;
import com.practice.ticket_master.dto.request.EventCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;
import com.practice.ticket_master.dto.response.EventSearchResponse;

import java.util.Optional;

public interface EventService {

    Optional<Event> get(Long id);

    ActionResponse create(EventCreateRequest request);

    EventSearchResponse getEventSearchResponse(EventSearchFilter filter);
}
