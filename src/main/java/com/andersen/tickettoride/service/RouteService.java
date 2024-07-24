package com.andersen.tickettoride.service;

import com.andersen.tickettoride.dto.RouteInputDto;
import com.andersen.tickettoride.dto.RouteOutputDto;
import com.andersen.tickettoride.mapper.CityMapper;
import com.andersen.tickettoride.mapper.RouteMapper;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Route;
import com.andersen.tickettoride.repository.CityRepository;
import com.andersen.tickettoride.repository.RouteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    @Autowired
    public RouteService(RouteRepository routeRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    public RouteOutputDto findPriceOfATicketOfRoute(RouteInputDto route) {

    }

    public Optional<Route> findById(Long id) {
        return routeRepository.findById(id);
    }

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Transactional
    public Route save(Route route) {
        return routeRepository.save(route);
    }

    @Transactional
    public Route update(Route route) {
        return save(route);
    }

    @Transactional
    public void delete(Route route) {
        routeRepository.delete(route);
    }
}
