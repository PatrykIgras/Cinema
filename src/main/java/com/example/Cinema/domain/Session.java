package com.example.Cinema.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "sessions")
@NamedEntityGraph(name = "Session.tickets", attributeNodes = @NamedAttributeNode("tickets"))
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "session_id")
    private Long id;
    @Column(name = "start_time")
    private LocalDateTime startTime;

    public Session() {
    }

    public Session(Long id, LocalDateTime startTime) {
        this.id = id;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", startTime=" + startTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;

        Session other = (Session) o;

        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Ticket> getTickets() {
        if (tickets == null) tickets = new ArrayList<>();
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        getTickets().add(ticket);
        ticket.setSession(this);
    }
}


