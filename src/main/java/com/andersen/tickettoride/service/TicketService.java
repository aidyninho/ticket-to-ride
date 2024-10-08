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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CityService cityService;
    private final RouteService routeService;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, CityService cityService, RouteService routeService, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.cityService = cityService;
        this.routeService = routeService;
        this.userService = userService;
    }


    /**
     * Get difference of two BigDecimal objects.
     *
     * @return difference's absolute value.
     */
    private BigDecimal getDifference(BigDecimal a, BigDecimal b) {
        return a.subtract(b).abs();
    }

    private TicketOutputOnSuccessDto getTicketOutputOnSuccessDto(
            TicketInputDto ticket, RouteOutputDto route, BigDecimal balanceLeft) {
        TicketOutputOnSuccessDto ticketOutputOnSuccessDto = new TicketOutputOnSuccessDto();
        ticketOutputOnSuccessDto.setSuccess(true);
        ticketOutputOnSuccessDto.setCurrency(ticket.getCurrency());
        ticketOutputOnSuccessDto.setChange(getDifference(route.getPrice(), balanceLeft));

        return ticketOutputOnSuccessDto;
    }

    private TicketOutputOnFailureDto getTicketOutputOnFailureDto(TicketInputDto ticket, User user, RouteOutputDto route) {
        TicketOutputOnFailureDto ticketOutputOnFailureDto = new TicketOutputOnFailureDto();
        ticketOutputOnFailureDto.setSuccess(false);
        ticketOutputOnFailureDto.setCurrency(ticket.getCurrency());
        ticketOutputOnFailureDto.setLackOf(getDifference(route.getPrice(), user.getBalance()));

        return ticketOutputOnFailureDto;
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    @Transactional
    public Ticket save(Ticket ticket) {
        Ticket savedTicket = ticketRepository.save(ticket);
        log.info("Ticket with id " + savedTicket.getId() + " was saved.");
        return savedTicket;
    }

    public TicketOutputDto save(TicketInputDto ticket) {
        City departureCity = cityService.findByName(ticket.getDeparture()).orElseThrow();
        City arrivalCity = cityService.findByName(ticket.getArrival()).orElseThrow();
        User user = userService.findByUsername(ticket.getUsername()).orElseThrow();

        RouteOutputDto route = routeService.findPriceOfATicketOfRoute(
                RouteInputDto.builder()
                        .departure(departureCity.getName())
                        .arrival(arrivalCity.getName())
                        .build()
        );

        if (user.getBalance().compareTo(route.getPrice()) < 0) {
            log.warn("User " + user.getUsername() + " doesn't have enough balance to save ticket.");

            return getTicketOutputOnFailureDto(ticket, user, route);
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
        BigDecimal balanceLeft = model.getUser().getBalance().subtract(route.getPrice());
        model.getUser().setBalance(balanceLeft);
        model.getUser().getTickets().add(model);

        return getTicketOutputOnSuccessDto(ticket, route, balanceLeft);
    }

    public Ticket update(Ticket ticket) {
        return save(ticket);
    }

    public void delete(Ticket ticket) {
        log.warn("Ticket with id " + ticket.getId() + " was deleted.");

        ticketRepository.delete(ticket);
    }
}
