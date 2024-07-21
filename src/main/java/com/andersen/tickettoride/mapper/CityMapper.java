package com.andersen.tickettoride.mapper;

import com.andersen.tickettoride.dto.CityCreateDto;
import com.andersen.tickettoride.dto.CityDto;
import com.andersen.tickettoride.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City toModel(CityDto dto);

    CityDto toDto(City model);

    City toModel(CityCreateDto dto);

    CityCreateDto toCreateDto(City model);
}
