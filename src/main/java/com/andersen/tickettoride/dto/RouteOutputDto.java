package com.andersen.tickettoride.dto;

import com.andersen.tickettoride.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteOutputDto {

    private Long segments;
    private BigDecimal price;
    private Currency currency;
}
