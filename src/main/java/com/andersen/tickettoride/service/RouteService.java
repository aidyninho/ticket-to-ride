package com.andersen.tickettoride.service;

import com.andersen.tickettoride.dto.RouteInputDto;
import com.andersen.tickettoride.dto.RouteOutputDto;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Currency;
import com.andersen.tickettoride.model.Route;
import com.andersen.tickettoride.repository.RouteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final GraphService graphService;
    private final CityService cityService;
    @Value("${app.currency}")
    private Currency DEFAULT_CURRENCY;
    @Value("${app.price.one-segment}")
    private double PRICE_FOR_ONE_SEGMENT;
    @Value("${app.price.two-segments}")
    private double PRICE_FOR_TWO_SEGMENTS;
    @Value("${app.price.three-segments}")
    private double PRICE_FOR_THREE_SEGMENTS;

    @Autowired
    public RouteService(RouteRepository routeRepository, GraphService graphService, CityService cityService) {
        this.routeRepository = routeRepository;
        this.graphService = graphService;
        this.cityService = cityService;
    }

    public BigDecimal evaluatePrice(long segments) {
        double price = 0;
        price += (segments / 3) * PRICE_FOR_THREE_SEGMENTS;
        segments %= 3;
        price += (segments / 2) * PRICE_FOR_TWO_SEGMENTS;
        segments %= 2;
        price += segments * PRICE_FOR_ONE_SEGMENT;

        return new BigDecimal(price);
    }

    public RouteOutputDto findPriceOfATicketOfRoute(RouteInputDto route) {
        if (graphService.isEmpty()) {
            graphService.createGraphFromRoutes(findAll());
        }

        City sourceCity = cityService.findByName(route.getDeparture()).orElseThrow();
        City destinationCity = cityService.findByName(route.getArrival()).orElseThrow();

        long shortestPathBetweenCities = getShortestSegmentsBetweenCities(sourceCity, destinationCity);

        return RouteOutputDto.builder()
                .segments(shortestPathBetweenCities)
                .currency(DEFAULT_CURRENCY)
                .price(evaluatePrice(shortestPathBetweenCities))
                .build();
    }

    private long getShortestSegmentsBetweenCities(City sourceCity, City destinationCity) {
        return (long) graphService.findShortestPathBetweenCities(sourceCity, destinationCity);
    }

    public Optional<Route> findById(Long id) {
        return routeRepository.findById(id);
    }

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Transactional
    public Route save(Route route) {
        graphService.addRouteToGraph(route);
        return routeRepository.save(route);
    }

    @Transactional
    public Route update(Route route) {
        return save(route);
    }

    @Transactional
    public void delete(Route route) {
        graphService.deleteRouteInGraph(route);
        routeRepository.delete(route);
    }
}
