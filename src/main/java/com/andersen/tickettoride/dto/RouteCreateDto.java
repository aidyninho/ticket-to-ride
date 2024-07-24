package com.andersen.tickettoride.dto;

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
public class RouteCreateDto {

    private Long id;
    private CityDto sourceCity;
    private CityDto destinationCity;
    private Long segments;
}
