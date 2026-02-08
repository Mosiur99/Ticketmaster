package com.practice.ticket_master.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActionResponse {

    private Boolean result;
    private String message;
}
