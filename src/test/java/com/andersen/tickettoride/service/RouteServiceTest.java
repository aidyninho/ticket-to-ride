package com.andersen.tickettoride.service;

import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Route;
import com.andersen.tickettoride.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    private final Long ID = 1L;
    private final Long SEGMENTS = 10L;
    private final City sourceCity = City.builder()
            .id(1L)
            .name("London")
            .build();
    private final City destinationCity = City.builder()
            .id(2L)
            .name("Bristol")
            .build();
    private final Route expectedRoute = Route.builder()
            .id(ID)
            .sourceCity(sourceCity)
            .destinationCity(destinationCity)
            .segments(SEGMENTS)
            .build();
    @Mock
    RouteRepository routeRepository;
    @Mock
    GraphService graphService;
    @InjectMocks
    RouteService routeService;

    @Test
    void findById_Returns_Route() {
        doReturn(Optional.of(expectedRoute)).when(routeRepository).findById(ID);

        Optional<Route> actualRoute = routeService.findById(ID);

        assertTrue(actualRoute.isPresent());
        assertEquals(Optional.of(expectedRoute), actualRoute);
    }

    @Test
    void findAll_Returns_List_Of_Routes() {
        List<Route> expectedRoutes = List.of(expectedRoute, expectedRoute);
        doReturn(expectedRoutes).when(routeRepository).findAll();

        List<Route> actualRoutes = routeService.findAll();

        assertEquals(expectedRoutes, actualRoutes);
    }

    @Test
    void save_Returns_Saved_Route() {
        Route route = Route.builder()
                .sourceCity(sourceCity)
                .destinationCity(destinationCity)
                .segments(SEGMENTS)
                .build();

        doReturn(false).when(graphService).isEmpty();
        doReturn(expectedRoute).when(routeRepository).save(route);

        Route actualRoute = routeService.save(route);

        assertNotNull(actualRoute);
        assertEquals(expectedRoute, actualRoute);
    }
}