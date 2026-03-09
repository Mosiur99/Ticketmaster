package com.practice.ticket_master.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventCreateRequest {

    private Long venueId;
    private Long performerId;
    private String name;
    private String description;
    private LocalDateTime eventTime;
}
