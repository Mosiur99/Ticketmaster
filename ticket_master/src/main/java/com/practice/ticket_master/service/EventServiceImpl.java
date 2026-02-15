package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Event;
import com.practice.ticket_master.domain.Performer;
import com.practice.ticket_master.domain.Venue;
import com.practice.ticket_master.dto.EventDTO;
import com.practice.ticket_master.dto.EventSearchFilter;
import com.practice.ticket_master.dto.request.EventCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;
import com.practice.ticket_master.dto.response.EventSearchResponse;
import com.practice.ticket_master.repository.EventRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final VenueService venueService;
    private final EventRepository eventRepository;
    private final PerformerService performerService;

    @Autowired
    public EventServiceImpl(VenueService venueService,
                            EventRepository eventRepository,
                            PerformerService performerService) {
        this.venueService = venueService;
        this.eventRepository = eventRepository;
        this.performerService = performerService;
    }

    @Override
    public Optional<Event> get(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    @Transactional
    public ActionResponse create(EventCreateRequest request) {
        ActionResponse validationResult = validateRequest(request);
        if (!validationResult.getResult()) {
            return validationResult;
        }

        Optional<Venue> venue = venueService.get(request.getVenueId());
        if (venue.isEmpty()) {
            return new ActionResponse(false, "Invalid venue provided");
        }

        Optional<Performer> performer = performerService.get(request.getPerformerId());
        if (performer.isEmpty()) {
            return new ActionResponse(false, "Invalid performer provided");
        }

        Event event = buildEvent(request, venue.get(), performer.get());
        eventRepository.save(event);
        return new ActionResponse(true, "Created successfully");
    }

    private Event buildEvent(EventCreateRequest request, Venue venue, Performer performer) {
        Event event = new Event();
        event.setVenue(venue);
        event.setPerformer(performer);
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        return event;
    }

    private ActionResponse validateRequest(EventCreateRequest request) {
        if (Objects.isNull(request)) {
            return failure("No request found");
        }

        if (Objects.isNull(request.getVenueId())) {
            return failure("Please provide venu");
        }

        if (Objects.isNull(request.getPerformerId())) {
            return failure("Please provide performer");
        }

        if (StringUtils.isBlank(request.getName())) {
            return failure("Please provide event name");
        }

        if (StringUtils.isBlank(request.getDescription())) {
            return failure("Please provide a short description");
        }

        return success();
    }

    private ActionResponse failure(String message) {
        return new ActionResponse(false, message);
    }

    private ActionResponse success() {
        return new ActionResponse(true, "Valid request");
    }

    @Override
    public EventSearchResponse getEventSearchResponse(EventSearchFilter filter) {
        if (Objects.isNull(filter)) {
            return new EventSearchResponse();
        }

        List<EventDTO> eventDTOS = new ArrayList<>();
        for (Long eventId : filter.getEventIds()) {
            Optional<Event> event = get(eventId);
            if (event.isEmpty()) {
                continue;
            }

            eventDTOS.add(buildEventDTO(event.get()));
        }

        return buildEventSearchResponse(eventDTOS);
    }

    private EventSearchResponse buildEventSearchResponse(List<EventDTO> eventDTOS) {
        EventSearchResponse response = new EventSearchResponse();
        response.setEventDTOS(eventDTOS);
        return response;
    }

    private EventDTO buildEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setName(event.getName());
        return eventDTO;
    }
}
