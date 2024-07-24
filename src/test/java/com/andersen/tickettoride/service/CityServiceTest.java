package com.andersen.tickettoride.service;

import com.andersen.tickettoride.model.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CityServiceTest {

    private static final long ID = 1L;
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
