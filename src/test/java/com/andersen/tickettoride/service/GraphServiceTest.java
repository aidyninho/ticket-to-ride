package com.andersen.tickettoride.service;

import com.andersen.tickettoride.model.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class GraphServiceTest {

    @Mock
    GraphService graphService;

    @Test
    void isEmpty_Returns_True() {
        doReturn(true).when(graphService).isEmpty();

        assertTrue(graphService.isEmpty());
    }

    @Test
    void findShortestPathBetweenCities_Returns_Double() {
        City london = City.builder()
                .id(1L)
                .name("London")
                .build();
        City bristol = City.builder()
                .id(1L)
                .name("Bristol")
                .build();

        doReturn(5.0).when(graphService).findShortestPathBetweenCities(london, bristol);

        double actual = graphService.findShortestPathBetweenCities(london, bristol);

        assertEquals(5.0, actual);
    }
}