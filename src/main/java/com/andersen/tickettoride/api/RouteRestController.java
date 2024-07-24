package com.andersen.tickettoride.api;

import com.andersen.tickettoride.dto.RouteInputDto;
import com.andersen.tickettoride.dto.RouteOutputDto;
import com.andersen.tickettoride.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/routes")
public class RouteRestController {

    private final RouteService routeService;

    @Autowired
    public RouteRestController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/find")
    public RouteOutputDto find(@RequestBody RouteInputDto routeInputDto) {
        return routeService.findPriceOfATicketOfRoute(routeInputDto);
    }
}
