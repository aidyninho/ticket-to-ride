package com.andersen.tickettoride.service;

import com.andersen.tickettoride.exception.CityAlreadyExistsException;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.repository.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    public Optional<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Transactional
    public City save(City city) {
        if (cityRepository.findByName(city.getName()).isPresent()) {
            throw new CityAlreadyExistsException();
        }
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
