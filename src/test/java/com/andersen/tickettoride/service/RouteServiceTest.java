package com.andersen.tickettoride.service;

import com.andersen.tickettoride.dto.RouteInputDto;
import com.andersen.tickettoride.dto.RouteOutputDto;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class RouteServiceTest {

    private static final long SOURCE_CITY_ID = 1L;
    private static final long DESTINATION_CITY_ID = 3L;
    private static final long SEGMENTS = 5L;
    private static final long ID = 36L;
    @Autowired
    private RouteService routeService;
    @Autowired
    private CityService cityService;
    private City sourceCity;
    private City destinationCity;
    private Route route;

    @BeforeEach
    public void setCity() {
        sourceCity = cityService.findById(SOURCE_CITY_ID).orElse(null);
        destinationCity = cityService.findById(DESTINATION_CITY_ID).orElse(null);
        route = Route.builder()
                .sourceCity(sourceCity)
                .destinationCity(destinationCity)
                .segments(SEGMENTS)
                .build();
    }

    @Test
    public void save() {
        City source = cityService.findById(SOURCE_CITY_ID).get();
        City destination = cityService.findById(DESTINATION_CITY_ID).get();

        RouteInputDto inputDto = RouteInputDto.builder()
                .departure(source.getName())
                .arrival(destination.getName())
                .build();

        RouteOutputDto outputDto = routeService.findPriceOfATicketOfRoute(inputDto);

        assertEquals(10L, outputDto.getSegments());
    }

    @Test
    public void evaluatePrice() {
        BigDecimal bigDecimal = routeService.evaluatePrice(1);
        bigDecimal = routeService.evaluatePrice(2);
        bigDecimal = routeService.evaluatePrice(3);
        bigDecimal = routeService.evaluatePrice(5);
        bigDecimal = routeService.evaluatePrice(6);
    }

    @Test
    public void update() {
        route.setId(ID);
        route.setSegments(5L);

        routeService.update(route);
        Route updatedRoute = routeService.findById(ID).orElse(null);

        assertEquals(5L, updatedRoute.getSegments());
    }

    @Test
    public void delete() {
        route = routeService.findById(ID).orElse(null);
        routeService.delete(route);

        Route deletedCity = routeService.findById(ID).orElse(null);

        assertNull(deletedCity);
    }

    @Test
    public void findById() {

    }
}
