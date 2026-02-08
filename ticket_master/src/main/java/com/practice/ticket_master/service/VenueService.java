package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Venue;

import java.util.Optional;

public interface VenueService {

    Optional<Venue> get(Long id);
}
