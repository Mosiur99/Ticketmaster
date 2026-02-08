package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Venue;
import com.practice.ticket_master.dto.request.VenueCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;
import com.practice.ticket_master.repository.VenueRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
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

    @Override
    @Transactional
    public ActionResponse create(VenueCreateRequest request) {
        ActionResponse validationResult = validateRequest(request);
        if (!validationResult.getResult()) {
            return validationResult;
        }

        Venue venue = buildEvent(request.getLocation());
        venueRepository.save(venue);
        return success("Created successfully");
    }

    private Venue buildEvent(String location) {
        Venue venue = new Venue();
        venue.setLocation(location);
        return venue;
    }

    private ActionResponse validateRequest(VenueCreateRequest request) {
        if (Objects.isNull(request)) {
            return failure("No request found");
        }

        if (StringUtils.isBlank(request.getLocation())) {
            return failure("Please provide a location");
        }

        return success("Valid request");
    }

    private ActionResponse failure(String message) {
        return new ActionResponse(false, message);
    }

    private ActionResponse success(String message) {
        return new ActionResponse(true, message);
    }
}
