package com.example.Cinema;

import com.example.Cinema.domain.*;
import com.example.Cinema.services.MarathonService;
import com.example.Cinema.services.MovieService;
import com.example.Cinema.services.SessionService;
import com.example.Cinema.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class CinemaRunner implements CommandLineRunner {

    private final static Logger LOG = LoggerFactory.getLogger(CinemaRunner.class);

    private MarathonService marathonService;
    private MovieService movieService;
    private SessionService sessionService;
    private TicketService ticketService;

    public CinemaRunner(MarathonService marathonService, MovieService movieService, SessionService sessionService, TicketService ticketService) {
        this.marathonService = marathonService;
        this.movieService = movieService;
        this.sessionService = sessionService;
        this.ticketService = ticketService;
    }

    @Override
    public void run(String... args) throws Exception {
        movieServiceInvocations();
        sessionServiceInvocations();
        ticketServiceInvocations();
        marathonServiceInvocations();
    }

    private void movieServiceInvocations() {
        Page<Movie> allMovies = movieService.getAllMovies(PageRequest.of(1, 3, Sort.by("title")));
        LOG.info("1. AllMovies. TotalElements={}, TotalPages={}", allMovies.getTotalElements(), allMovies.getTotalPages());
        allMovies.get().forEach(movie -> LOG.info("     {}", movie));

        Page<Movie> moviesByPartOfTitle = movieService.getMoviesByPartOfTitle("gry", Pageable.unpaged());
        LOG.info("2. MoviesByPartOfTitle. TotalElements={}, TotalPages={}", moviesByPartOfTitle.getTotalElements(), moviesByPartOfTitle.getTotalPages());
        moviesByPartOfTitle.get().forEach(movie -> LOG.info("   {}", movie));

        Page<Movie> comedyMovies = movieService.getMoviesInCategory(EMovieCategory.COMEDY, Pageable.unpaged());
        LOG.info("3. ComedyMovies. TotalElements={}, TotalPages={}", comedyMovies.getTotalElements(), comedyMovies.getTotalPages());
        comedyMovies.get().forEach(movie -> LOG.info("  {}", movie));

        Movie movieWithId7 = movieService.getMovie(7L).get();
        LOG.info("4. movieWithId7 - {}", movieWithId7);
        movieService.updateMovie(7L, movieWithId7.getTitle(), EMovieCategory.DRAMA, 140, movieWithId7.getDescription(), 18, "/posterGreenBook.png");

        Movie movieWithId7AfterUpdate = movieService.getMovie(7L).get();
        LOG.info("5. movieWithId7AfterUpdate - {}", movieWithId7AfterUpdate);

        Long newMovieId = movieService.createMovie("Jak wytresować smoka", EMovieCategory.FAMILY, 104, "Witamy w siecie smoków...", 7, null);
        Movie newMovie = movieService.getMovie(newMovieId).get();
        LOG.info("6. newMovie - {}", newMovie);

        movieService.removeMovie(5L);
    }

    private void marathonServiceInvocations() {
        Long newMarathonId = marathonService.createMarathon("Maraton horrorów", LocalDateTime.of(2018, 10, 30, 20, 0), Arrays.asList(6L, 8L, 9L));
        Marathon newMarathon = marathonService.getMarathon(newMarathonId).get();
        LOG.info("14. newMarathon - {}", newMarathon);
        newMarathon.getMovies().forEach(movie -> LOG.info("     {}",movie));

        List<Marathon> marathons = marathonService.getAllMarathons();
        LOG.info("15. marathons. AmountOfMarathons={}", marathons.size());
        marathons.forEach(marathon -> LOG.info("    {}", marathon));

        marathonService.removeMarathon(newMarathonId);

    }

    private void ticketServiceInvocations() {
        List<Ticket> ticcketsOnSessionWithId15 = ticketService.getAllTicketsForSession(15L);
        LOG.info("11. ticcketsOnSessionWithId15. AmountOfTickets={}", ticcketsOnSessionWithId15.size());
        ticcketsOnSessionWithId15.forEach(ticket -> LOG.info("  {}", ticket));

        Long newTicketId = ticketService.newTicket(15L, "r2m5", new BigDecimal("22.50"));
        LOG.info("12. newTicketId={}", newTicketId);

        ticketService.cancelTicket(18L);

        List<Ticket> ticketOnSessionWithId15AfterUpdates = ticketService.getAllTicketsForSession(15L);
        LOG.info("13. ticketOnSessionWithId15AfterUpdates. AmountOfTickets={}", ticketOnSessionWithId15AfterUpdates.size());
        ticketOnSessionWithId15AfterUpdates.forEach(ticket -> LOG.info("    {}", ticket));
    }

    private void sessionServiceInvocations() {
        List<Session> sessionsIn02052019 = sessionService.getSessionInDate(LocalDate.of(2019, 5, 2));
        LOG.info("7. sessionsIn02052019. AmountOfSessions={}", sessionsIn02052019.size());
        sessionsIn02052019.forEach(session -> LOG.info("    {}", session));

        Session sessionWithId15 = sessionService.getSession(15L).get();
        LOG.info("8. sessionWithId15 - {}, MovieTitle='{}', Room='{}'", sessionWithId15, sessionWithId15.getMovie().getTitle(), sessionWithId15.getRoom().getName());
//        sessionWithId15.getTickets().forEach(ticket -> LOG.info("   {}", ticket));

        Session sessionWithId15WithTickets = sessionService.getSessionWithTickets(15L).get();
        LOG.info("9. sessionWithId15WithTickets - {}, MovieTitle='{}', Room='{}'", sessionWithId15WithTickets, sessionWithId15WithTickets.getMovie().getTitle(), sessionWithId15WithTickets.getRoom().getName());
        sessionWithId15WithTickets.getTickets().forEach(ticket -> LOG.info("    {}",ticket));

        Long newSessionId = sessionService.createSession(11L, 4L, LocalDateTime.of(2019, 5, 3, 10, 30));
        Session newSession = sessionService.getSession(newSessionId).get();
        LOG.info("10. newSession - {}", newSession);

        sessionService.removeSession(16L);
    }
}
