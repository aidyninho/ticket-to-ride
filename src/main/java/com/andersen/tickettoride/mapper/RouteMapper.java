package com.andersen.tickettoride.mapper;

import com.andersen.tickettoride.dto.CityCreateDto;
import com.andersen.tickettoride.dto.CityDto;
import com.andersen.tickettoride.dto.RouteCreateDto;
import com.andersen.tickettoride.dto.RouteDto;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    Route toModel(RouteDto dto);

    RouteDto toDto(Route model);

    Route toModel(RouteCreateDto dto);

    RouteCreateDto toCreateDto(Route model);
}
