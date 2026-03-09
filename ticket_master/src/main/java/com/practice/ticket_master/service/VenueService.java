package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Venue;
import com.practice.ticket_master.dto.request.VenueCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;

import java.util.Optional;

public interface VenueService {

    Optional<Venue> get(Long id);

    ActionResponse create(VenueCreateRequest request);
}
