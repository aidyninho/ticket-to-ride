package com.andersen.tickettoride.dto;

import com.andersen.tickettoride.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TicketInputDto {

    private String departure;
    private String arrival;
    private Currency currency;
    private String username;
}
