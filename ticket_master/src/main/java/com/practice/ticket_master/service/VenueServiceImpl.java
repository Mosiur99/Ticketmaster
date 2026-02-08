package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Venue;
import com.practice.ticket_master.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VenueServiceImpl implements VenueService{

    private final VenueRepository venueRepository;

    @Autowired
    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Optional<Venue> get(Long id) {
        return venueRepository.findById(id);
    }
}
