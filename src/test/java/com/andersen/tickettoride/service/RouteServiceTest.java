package com.andersen.tickettoride.service;

import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class RouteServiceTest {

    private static final long SOURCE_CITY_ID = 30L;
    private static final long DESTINATION_CITY_ID = 31L;
    private static final long SEGMENTS = 1L;
    private static final long ID = 29L;
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
        routeService.save(route);

        Route savedRoute = routeService.findById(ID).orElse(null);

        assertEquals(ID, savedRoute.getId());
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
