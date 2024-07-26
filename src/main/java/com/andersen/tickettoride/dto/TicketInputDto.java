package com.andersen.tickettoride.dto;

import com.andersen.tickettoride.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketInputDto {

    private String departure;
    private String arrival;
    private Currency currency;
    private String username;
}
