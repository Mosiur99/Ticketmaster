package com.practice.ticket_master_search_service.service;

import com.practice.ticket_master_search_service.domain.EventDocument;
import com.practice.ticket_master_search_service.dto.EventSearchFilter;
import com.practice.ticket_master_search_service.dto.EventSearchResponse;
import com.practice.ticket_master_search_service.repository.EventSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class EventSearchServiceImpl implements EventSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventSearchServiceImpl.class);

    private final String eventMainServiceBaseUrl;
    private final EventSearchRepository eventSearchRepository;

    @Autowired
    public EventSearchServiceImpl(@Value("${event.main.service.base.url}") String eventMainServiceBaseUrl,
                                  EventSearchRepository eventSearchRepository) {
        this.eventMainServiceBaseUrl = eventMainServiceBaseUrl;
        this.eventSearchRepository = eventSearchRepository;
    }

    @Override
    public EventSearchResponse getEventSearchResponse(EventSearchFilter filter) {
        try {
            String url = UriComponentsBuilder
                    .fromUriString(eventMainServiceBaseUrl + "/event/search")
                    .queryParam("eventIds", filter.getEventIds())
                    .toUriString();

            RestTemplate restTemplate = new RestTemplate();

            return restTemplate.getForObject(url, EventSearchResponse.class);
        } catch (Exception e) {
            LOGGER.error("Error: getEventSearchResponse.\nMessage:{}\nDetails:{}", e.getMessage(), e);
            return new EventSearchResponse();
        }
    }

    @Override
    @Transactional
    public void save(EventDocument eventDocument) {
        eventSearchRepository.save(eventDocument);
    }

    @Override
    public List<EventDocument> searchByName(String name) {
        return eventSearchRepository.findByName(name);
    }
}
