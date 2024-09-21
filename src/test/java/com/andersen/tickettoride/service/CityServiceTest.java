package com.andersen.tickettoride.service;

import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    private final Long ID = 1L;
    private final String NAME = "London";
    private final City expectedCity = City.builder()
            .id(ID)
            .name(NAME)
            .build();
    @Mock
    CityRepository cityRepository;
    @InjectMocks
    CityService cityService;

    @Test
    void findById_Returns_City() {
        doReturn(Optional.of(expectedCity)).when(cityRepository).findById(ID);

        Optional<City> actualCity = cityService.findById(ID);

        assertTrue(actualCity.isPresent());
        assertEquals(Optional.of(expectedCity), actualCity);
    }

    @Test
    void findByName_Returns_City() {
        doReturn(Optional.of(expectedCity)).when(cityRepository).findByName(NAME);

        Optional<City> actualCity = cityService.findByName(NAME);

        assertTrue(actualCity.isPresent());
        assertEquals(Optional.of(expectedCity), actualCity);
    }

    @Test
    void save_Returns_Saved_City() {
        City city = City.builder()
                .name("London")
                .build();

        doReturn(expectedCity).when(cityRepository).save(city);

        City actualCity = cityService.save(city);

        assertNotNull(actualCity);
        assertEquals(expectedCity, actualCity);
    }
}