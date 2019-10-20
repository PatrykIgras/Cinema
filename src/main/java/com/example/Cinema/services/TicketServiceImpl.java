package com.example.Cinema.services;

import com.example.Cinema.domain.Session;
import com.example.Cinema.domain.Ticket;
import com.example.Cinema.exceptions.BussinesException;
import com.example.Cinema.exceptions.EntityDoesNotExistException;
import com.example.Cinema.repository.SessionRepository;
import com.example.Cinema.repository.TicketRepository;
import com.example.Cinema.utils.IterableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private SessionRepository sessionRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, SessionRepository sessionRepository) {
        this.ticketRepository = ticketRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).get();
    }

    @Override
    @Transactional
    public Long newTicket(Long sessionId, String seat, BigDecimal price) {
        Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
        if (!sessionOptional.isPresent()) throw new EntityDoesNotExistException("Session, id: " + sessionId);

        Long amountOfTicketInSession = ticketRepository.countBySession(sessionOptional.get());
        if (amountOfTicketInSession == sessionOptional.get().getRoom().getCapacity()) {
            throw new BussinesException("All tickets have been sold out, session id: " + sessionId);
        }

        Ticket ticket = new Ticket(null, seat, price);
        sessionOptional.get().addTicket(ticket);
        ticketRepository.save(ticket);

        return ticket.getId();
    }

    @Override
    public List<Ticket> getAllTicketsForSession(Long sessionId) {
        Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
        if (!sessionOptional.isPresent()) throw new EntityDoesNotExistException("Session, id: " + sessionId);

        Iterable<Ticket> tickets = ticketRepository.findAllBySession(sessionOptional.get());

        return IterableUtils.iterableToList(tickets);
    }

    @Override
    @Transactional
    public void cancelTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
