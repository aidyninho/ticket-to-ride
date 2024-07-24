package com.andersen.tickettoride.repository;

import com.andersen.tickettoride.model.Ticket;
import com.andersen.tickettoride.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
