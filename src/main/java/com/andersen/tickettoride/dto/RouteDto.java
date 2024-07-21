package com.andersen.tickettoride.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class RouteDto {

    private Long id;
    private Long sourceCityId;
    private Long destinationCityId;
    private Long segments;
}
