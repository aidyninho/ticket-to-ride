package com.andersen.tickettoride.mapper;

import com.andersen.tickettoride.dto.CityCreateDto;
import com.andersen.tickettoride.dto.CityDto;
import com.andersen.tickettoride.model.City;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City toModel(CityDto dto);

    CityDto toDto(City model);

    default City toModel(CityCreateDto dto) {
        return City.builder()
                .name(dto.getName())
                .build();
    }

    CityCreateDto toCreateDto(City model);

    default void copy(City model, CityCreateDto dto) {
        model.setId(dto.getId());
        model.setName(dto.getName());
    }
}
