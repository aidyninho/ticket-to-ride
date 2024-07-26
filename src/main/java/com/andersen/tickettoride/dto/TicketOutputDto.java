package com.andersen.tickettoride.dto;

import com.andersen.tickettoride.model.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class TicketOutputDto {

    boolean success;
    private Currency currency;
}
