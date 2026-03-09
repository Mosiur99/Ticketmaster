package com.practice.ticket_master.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeatCreateRequest {

    private Long venueId;
    private List<SectionSeatRequest> sectionSeatRequests;
}
