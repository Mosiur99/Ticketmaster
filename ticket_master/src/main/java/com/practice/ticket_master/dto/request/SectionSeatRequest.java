package com.practice.ticket_master.dto.request;

import com.practice.ticket_master.enumeration.SeatSection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionSeatRequest {

    private SeatSection section;
    private Integer seatCount;
}
