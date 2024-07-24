package com.andersen.tickettoride.service;

import com.andersen.tickettoride.dto.RouteInputDto;
import com.andersen.tickettoride.dto.RouteOutputDto;
import com.andersen.tickettoride.dto.TicketInputDto;
import com.andersen.tickettoride.dto.TicketOutputDto;
import com.andersen.tickettoride.dto.TicketOutputOnFailureDto;
import com.andersen.tickettoride.dto.TicketOutputOnSuccessDto;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Ticket;
import com.andersen.tickettoride.model.User;
import com.andersen.tickettoride.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CityService cityService;
    private final RouteService routeService;
    private final UserService userService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, CityService cityService, RouteService routeService, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.cityService = cityService;
        this.routeService = routeService;
        this.userService = userService;
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    @Transactional
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public TicketOutputDto save(TicketInputDto ticket) {
        // TODO: 25.07.2024 make it readable
        City departureCity = cityService.findByName(ticket.getDeparture()).orElseThrow();
        City arrivalCity = cityService.findByName(ticket.getArrival()).orElseThrow();
        User user = userService.findByUsername(ticket.getUsername()).orElseThrow();

        RouteOutputDto route = routeService.findPriceOfATicketOfRoute(RouteInputDto.builder()
                .departure(departureCity.getName())
                .arrival(arrivalCity.getName())
                .build());

        if (user.getBalance().compareTo(route.getPrice()) < 0) {
            TicketOutputOnFailureDto ticketOutputOnFailureDto = new TicketOutputOnFailureDto();
            ticketOutputOnFailureDto.setSuccess(false);
            ticketOutputOnFailureDto.setCurrency(ticket.getCurrency());
            ticketOutputOnFailureDto.setLackOf(getDifference(route.getPrice(), user.getBalance()));
            return ticketOutputOnFailureDto;
        }

        Ticket model = Ticket.builder()
                .user(user)
                .sourceCity(departureCity)
                .destinationCity(arrivalCity)
                .segments(route.getSegments())
                .price(route.getPrice())
                .currency(ticket.getCurrency())
                .build();

        save(model);
        model.getUser().setBalance(model.getUser().getBalance().subtract(route.getPrice()));
        model.getUser().getTickets().add(model);

        TicketOutputOnSuccessDto ticketOutputOnSuccessDto = new TicketOutputOnSuccessDto();
        ticketOutputOnSuccessDto.setSuccess(true);
        ticketOutputOnSuccessDto.setCurrency(ticket.getCurrency());
        ticketOutputOnSuccessDto.setChange(getDifference(route.getPrice(), user.getBalance()));
        return ticketOutputOnSuccessDto;
    }

    private BigDecimal getDifference(BigDecimal a, BigDecimal b) {
        return a.subtract(b).abs();
    }

    @Transactional
    public Ticket update(Ticket ticket) {
        return save(ticket);
    }

    @Transactional
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
}
