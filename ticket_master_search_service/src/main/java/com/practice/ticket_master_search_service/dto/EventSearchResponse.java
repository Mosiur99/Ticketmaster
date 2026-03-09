package com.practice.ticket_master_search_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class EventSearchResponse {

    private List<EventDTO> eventDTOS = Collections.emptyList();
}
