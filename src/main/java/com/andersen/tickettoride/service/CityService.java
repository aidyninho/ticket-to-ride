package com.andersen.tickettoride.service;

import com.andersen.tickettoride.exception.CityAlreadyExistsException;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.repository.CityRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

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
        City savedCity = cityRepository.save(city);

        log.info("City " + city.getName() + " was saved.");

        return savedCity;
    }

    @Transactional
    public City update(City city) {
        return save(city);
    }

    @Transactional
    public void delete(City city) {
        cityRepository.delete(city);

        log.info("City " + city.getName() + " was deleted.");
    }
}
