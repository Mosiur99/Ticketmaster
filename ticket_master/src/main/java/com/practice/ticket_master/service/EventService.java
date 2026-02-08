package com.practice.ticket_master.service;

import com.practice.ticket_master.dto.request.EventCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;

public interface EventService {

    ActionResponse create(EventCreateRequest request);
}
