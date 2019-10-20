package com.example.Cinema.controllers;

import com.example.Cinema.domain.Ticket;
import com.example.Cinema.services.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/getTicketsOnSession")
    public List<Ticket> getAllTicketsForSession(@RequestParam("session_id") Long id){
        return ticketService.getAllTicketsForSession(id);
    }

    @PostMapping("/add")
    public Ticket addTicket(@RequestBody Ticket ticket, @RequestParam("session_id") Long id){
        Long newTicketId = ticketService.newTicket(id, ticket.getSeat(), ticket.getPrice());
        return ticketService.getTicketById(newTicketId);
    }



}
