package com.andersen.tickettoride.api;

import com.andersen.tickettoride.dto.TicketInputDto;
import com.andersen.tickettoride.dto.TicketOutputDto;
import com.andersen.tickettoride.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketRestController {

    private final TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/save")
    public TicketOutputDto save(@RequestBody TicketInputDto ticketInputDto) {
        return ticketService.save(ticketInputDto);
    }
}
