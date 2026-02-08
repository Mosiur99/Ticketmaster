package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Performer;
import com.practice.ticket_master.repository.PerformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerformerServiceImpl implements PerformerService{

    private final PerformerRepository performerRepository;

    @Autowired
    public PerformerServiceImpl(PerformerRepository performerRepository) {
        this.performerRepository = performerRepository;
    }

    @Override
    public Optional<Performer> get(Long id) {
        return performerRepository.findById(id);
    }
}
