package com.andersen.tickettoride.api;

import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cities")
public class CityRestController {

    private final CityService cityService;

    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/save")
    public City save(@RequestBody City ticketInputDto) {
        return cityService.save(ticketInputDto);
    }
}
