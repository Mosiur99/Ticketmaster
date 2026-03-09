package com.practice.ticket_master_search_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventSearchFilter {

    private List<Long> eventIds;
}
