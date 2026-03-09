package com.practice.ticket_master.service;

import com.practice.ticket_master.dto.request.SeatCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;

public interface SeatService {

    ActionResponse create(SeatCreateRequest request);
}
