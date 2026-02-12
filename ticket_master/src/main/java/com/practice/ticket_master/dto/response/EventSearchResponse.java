package com.practice.ticket_master.dto.response;

import com.practice.ticket_master.dto.EventDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class EventSearchResponse {

    private List<EventDTO> eventDTOS = Collections.emptyList();
}
