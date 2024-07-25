package com.andersen.tickettoride.api;

import com.andersen.tickettoride.dto.ExceptionDto;
import com.andersen.tickettoride.dto.RouteCreateDto;
import com.andersen.tickettoride.dto.RouteInputDto;
import com.andersen.tickettoride.dto.RouteOutputDto;
import com.andersen.tickettoride.exception.CityNotFoundException;
import com.andersen.tickettoride.exception.RouteAlreadyExistsException;
import com.andersen.tickettoride.exception.UsernameAlreadyExistsException;
import com.andersen.tickettoride.model.Route;
import com.andersen.tickettoride.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/routes")
@Slf4j
public class RouteRestController {

    private final RouteService routeService;

    @Autowired
    public RouteRestController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public RouteOutputDto find(@RequestBody RouteInputDto routeInputDto) {
        return routeService.findPriceOfATicketOfRoute(routeInputDto);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Route save(@RequestBody RouteCreateDto route) {
        return routeService.save(route);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CityNotFoundException.class)
    public ExceptionDto handleCityNotFoundException() {
        log.warn("Exception " + CityNotFoundException.class.getSimpleName() + " was handled.");
        return ExceptionDto.builder()
                .reason("City not found")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RouteAlreadyExistsException.class)
    public ExceptionDto handleRouteAlreadyExistsException() {
        log.warn("Exception " + RouteAlreadyExistsException.class.getSimpleName() + " was handled.");
        return ExceptionDto.builder()
                .reason("Route already exists")
                .build();
    }
}
