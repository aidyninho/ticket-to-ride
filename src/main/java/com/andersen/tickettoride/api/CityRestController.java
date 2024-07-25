package com.andersen.tickettoride.api;

import com.andersen.tickettoride.dto.ExceptionDto;
import com.andersen.tickettoride.exception.CityAlreadyExistsException;
import com.andersen.tickettoride.exception.UsernameAlreadyExistsException;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CityAlreadyExistsException.class)
    public ExceptionDto handleCityAlreadyExistsException() {
        return ExceptionDto.builder()
                .reason("City already exists")
                .build();
    }
}
