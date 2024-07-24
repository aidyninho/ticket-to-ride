package com.andersen.tickettoride;

import com.andersen.tickettoride.dto.CityCreateDto;
import com.andersen.tickettoride.dto.CityDto;
import com.andersen.tickettoride.dto.RouteCreateDto;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.service.CityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class TicketToRideApplicationTests {

    private static final long ID = 29L;
    @Autowired
    private CityService cityService;

    @Test
    public void save() {
        City city = City.builder()
                .name("test123")
                .build();

        City savedCity = cityService.save(city);

        assertEquals(ID, savedCity.getId());
    }

    @Test
    public void update() {
        City city = cityService.findById(ID).orElse(null);

        city.setName("test777");

        cityService.update(city);
        City updatedCity = cityService.findById(ID).orElse(null);

        assertEquals("test777", updatedCity.getName());
    }

    @Test
    public void delete() {
        City city = cityService.findById(ID).orElse(null);

        cityService.delete(city);

        City deletedCity = cityService.findById(ID).orElse(null);

        assertNull(deletedCity);
    }

    @Test
    public void findById() {
        City city = cityService.findById(24L).orElse(null);

        cityService.delete(city);

        City deletedCity = cityService.findById(ID).orElse(null);

        assertNull(deletedCity);
    }
}
