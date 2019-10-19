package com.example.Cinema.repository;

import com.example.Cinema.domain.Session;
import com.example.Cinema.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long>{
    Iterable<Ticket> findAllBySession(Session session);
    Long countBySession(Session session);

}
