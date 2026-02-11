package com.practice.ticket_master_search_service.service;

import ch.qos.logback.core.joran.spi.HttpUtil;
import com.practice.ticket_master_search_service.dto.EventSearchFilter;
import com.practice.ticket_master_search_service.dto.EventSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    private final String eventMainServiceUrl;

    @Autowired
    public SearchServiceImpl(@Value("${event.main.service.url}") String eventMainServiceUrl) {
        this.eventMainServiceUrl = eventMainServiceUrl;
    }

    @Override
    public EventSearchResponse getEventSearchResponse(EventSearchFilter filter) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = eventMainServiceUrl + "/events";
            return restTemplate.getForObject(url, EventSearchResponse.class);
        } catch (Exception e) {
            LOGGER.error("Error: getEventSearchResponse.\nMessage:{}\nDetails:{}", e.getMessage(), e);
            return new EventSearchResponse();
        }
    }
}
