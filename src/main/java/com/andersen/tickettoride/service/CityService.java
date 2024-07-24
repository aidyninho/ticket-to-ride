package com.andersen.tickettoride.service;

import com.andersen.tickettoride.dto.CityCreateDto;
import com.andersen.tickettoride.dto.CityDto;
import com.andersen.tickettoride.mapper.CityMapper;
import com.andersen.tickettoride.mapper.RouteMapper;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.repository.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final RouteMapper routeMapper;

    @Autowired
    public CityService(CityRepository cityRepository, CityMapper cityMapper, RouteMapper routeMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.routeMapper = routeMapper;
    }

    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Transactional
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Transactional
    public City update(City city) {
        return save(city);
    }

    @Transactional
    public void delete(City city) {
        cityRepository.delete(city);
    }
}
