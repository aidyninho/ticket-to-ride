package com.andersen.tickettoride.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TicketOutputOnFailureDto extends TicketOutputDto {

    private BigDecimal lackOf;
}
