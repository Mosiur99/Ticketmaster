package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Performer;
import com.practice.ticket_master.dto.request.PerformerCreateRequest;
import com.practice.ticket_master.dto.request.VenueCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;

import java.util.Optional;

public interface PerformerService {

    Optional<Performer> get(Long id);

    ActionResponse create(PerformerCreateRequest request);
}
