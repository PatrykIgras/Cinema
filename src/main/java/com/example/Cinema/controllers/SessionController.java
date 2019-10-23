package com.example.Cinema.controllers;

import com.example.Cinema.domain.Session;
import com.example.Cinema.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {

    private SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/addSession")
    public Session createSession(@RequestBody Session session) {
        Long sessionId = sessionService.createSession(session.getMovie().getId(), session.getRoom().getId(), session.getStartTime());
        return sessionService.getSession(sessionId).get();
    }

    @GetMapping("/getById")
    public Session getSession(@RequestParam("sessionId") Long sessionId) {
        return sessionService.getSession(sessionId).get();
    }

    @GetMapping("/getSessionWithTickets")
    public Session getSessionWithTickets(@RequestParam("sessionId") Long sessionId) {
        return sessionService.getSessionWithTickets(sessionId).get();
    }

    @GetMapping("/getSessionInDate")
    public List<Session> getSessionInDate(@RequestBody LocalDate date) {
        return sessionService.getSessionInDate(date);
    }

    @DeleteMapping("/deleteById")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeSession(@RequestParam Long sessionId) {
        sessionService.removeSession(sessionId);
    }
}
