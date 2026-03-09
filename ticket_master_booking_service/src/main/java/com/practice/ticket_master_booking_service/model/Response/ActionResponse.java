package com.practice.ticket_master_booking_service.model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActionResponse {

    private Boolean result;
    private String message;
}
