package com.andersen.tickettoride.service;

import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Currency;
import com.andersen.tickettoride.model.Ticket;
import com.andersen.tickettoride.model.User;
import com.andersen.tickettoride.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketServiceTest {

    private final long ID = 1L;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private CityService cityService;
    @Mock
    private RouteService routeService;
    @Mock
    private UserService userService;
    @InjectMocks
    private TicketService ticketService;

    @Test
    void findById_Returns_Ticket() {
        Ticket ticket = Ticket.builder()
                .id(ID)
                .user(User.builder().build())
                .price(BigDecimal.TEN)
                .sourceCity(City.builder().build())
                .destinationCity(City.builder().build())
                .currency(Currency.GBP)
                .segments(3L)
                .build();

        doReturn(Optional.of(ticket)).when(ticketRepository).findById(ID);

        Optional<Ticket> actualTicket = ticketService.findById(ID);

        assertTrue(actualTicket.isPresent());
        assertEquals(Optional.of(ticket), actualTicket);
    }

    @Test
    void save_Returns_Ticket() {
        Ticket ticket = Ticket.builder()
                .id(ID)
                .user(User.builder().build())
                .price(BigDecimal.TEN)
                .sourceCity(City.builder().build())
                .destinationCity(City.builder().build())
                .currency(Currency.GBP)
                .segments(3L)
                .build();
        Ticket ticketWithoutId = Ticket.builder()
                .user(User.builder().build())
                .price(BigDecimal.TEN)
                .sourceCity(City.builder().build())
                .destinationCity(City.builder().build())
                .currency(Currency.GBP)
                .segments(3L)
                .build();

        doReturn(ticket).when(ticketRepository).save(ticketWithoutId);

        Ticket actualTicket = ticketService.save(ticketWithoutId);

        assertEquals(ticket, actualTicket);
    }
}