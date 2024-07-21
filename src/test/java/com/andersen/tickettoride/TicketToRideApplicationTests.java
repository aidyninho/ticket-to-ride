package com.andersen.tickettoride;

import com.andersen.tickettoride.dto.CityCreateDto;
import com.andersen.tickettoride.dto.CityDto;
import com.andersen.tickettoride.dto.RouteCreateDto;
import com.andersen.tickettoride.mapper.CityMapper;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Route;
import com.andersen.tickettoride.repository.CityRepository;
import com.andersen.tickettoride.service.CityService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TicketToRideApplicationTests {

    @Autowired
    private CityService cityService;

    @Test
    public void save() {
        City swindon = City.builder()
                .name("test")
                .build();

        cityService.save(swindon);

        City byId = cityService.findById(5L);

        Route route = Route.builder()
                .sourceCity(swindon)
                .destinationCity(cityService.findById(1L))
                .segments(4L).build();

        swindon.setRoutes(List.of(route));

        cityService.save(swindon);
    }
}
