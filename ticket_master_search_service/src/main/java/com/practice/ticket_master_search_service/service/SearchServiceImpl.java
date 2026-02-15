package com.practice.ticket_master_search_service.service;

import com.practice.ticket_master_search_service.dto.EventSearchFilter;
import com.practice.ticket_master_search_service.dto.EventSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    private final String eventMainServiceBaseUrl;

    @Autowired
    public SearchServiceImpl(@Value("${event.main.service.base.url}") String eventMainServiceBaseUrl) {
        this.eventMainServiceBaseUrl = eventMainServiceBaseUrl;
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
}
