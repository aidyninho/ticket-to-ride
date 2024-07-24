package com.andersen.tickettoride.service;

import com.andersen.tickettoride.dto.TicketInputDto;
import com.andersen.tickettoride.dto.TicketOutputDto;
import com.andersen.tickettoride.model.Currency;
import com.andersen.tickettoride.model.Role;
import com.andersen.tickettoride.model.Ticket;
import com.andersen.tickettoride.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TicketServiceTest {

    private static final long ID = 1L;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    private TicketInputDto ticket;
    private User user;

    @BeforeEach
    public void setUser() {
        user = userService.findById(3L).orElse(null);
        ticket = TicketInputDto.builder()
                .currency(Currency.GBP)
                .departure("Coventry")
                .arrival("Reading")
                .build();
    }

    @Test
    public void save() {
        TicketOutputDto save = ticketService.save(ticket);

        Optional<Ticket> byId = ticketService.findById(ID);
        assertEquals(ID, byId.get().getId());
    }

    @Test
    public void update() {
        User user1 = userService.findById(4L).orElse(null);

        user1.setUsername("asdf");
        user1.setPassword("asdf");
        user1.setRole(Role.ADMIN);
        user1.setBalance(new BigDecimal("2134"));

        userService.update(user1);
    }

    @Test
    public void delete() {
        userService.delete(userService.findById(4L).orElse(null));
    }

    @Test
    public void findById() {

    }
}
