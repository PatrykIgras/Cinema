package com.example.Cinema.services;

import com.example.Cinema.domain.Ticket;

import java.math.BigDecimal;
import java.util.List;

public interface TicketService {
    Long newTicket(Long sessionId, String seat, BigDecimal price);

    List<Ticket> getAllTicketsForSession(Long sessionId);
    Ticket getTicketById(Long ticketId);
    void cancelTicket(Long ticketId);
}
