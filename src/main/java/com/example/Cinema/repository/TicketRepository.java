package com.example.Cinema.repository;

import com.example.Cinema.domain.Session;
import com.example.Cinema.domain.Ticket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Iterable<Ticket> findAllBySession(Session session);
    Long countBySession(Session session);
}
