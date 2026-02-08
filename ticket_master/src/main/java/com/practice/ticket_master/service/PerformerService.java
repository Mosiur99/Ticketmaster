package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Performer;

import java.util.Optional;

public interface PerformerService {

    Optional<Performer> get(Long id);
}
