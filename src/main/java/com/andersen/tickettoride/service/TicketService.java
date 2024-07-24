package com.andersen.tickettoride.service;

import com.andersen.tickettoride.model.Ticket;
import com.andersen.tickettoride.model.User;
import com.andersen.tickettoride.repository.TicketRepository;
import com.andersen.tickettoride.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    @Transactional
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
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
