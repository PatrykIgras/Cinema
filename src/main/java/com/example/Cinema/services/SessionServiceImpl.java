package com.example.Cinema.services;

import com.example.Cinema.domain.Movie;
import com.example.Cinema.domain.Room;
import com.example.Cinema.domain.Session;
import com.example.Cinema.exceptions.EntityDoesNotExistException;
import com.example.Cinema.repository.MovieRepository;
import com.example.Cinema.repository.RoomRepository;
import com.example.Cinema.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;
    private MovieRepository movieRepository;
    private RoomRepository roomRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, MovieRepository movieRepository, RoomRepository roomRepository) {
        this.sessionRepository = sessionRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public Long createSession(Long movieId, Long roomId, LocalDateTime startTime) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (!movieOptional.isPresent()) throw new EntityDoesNotExistException("Movie, id: " + movieId);

        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (!roomOptional.isPresent()) throw new EntityDoesNotExistException("Room, id: " + roomId);

        Session session = new Session(null, startTime);
        session.setMovie(movieOptional.get());
        session.setRoom(roomOptional.get());

        sessionRepository.save(session);

        return session.getId();
    }

    @Override
    public Optional<Session> getSession(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Override
    public Optional<Session> getSessionWithTickets(Long sessionId) {
        return sessionRepository.readById(sessionId);
    }

    @Override
    public List<Session> getSessionInDate(LocalDate date) {
        return sessionRepository.findAllByStartDate(java.sql.Date.valueOf(date));
    }

    @Override
    public void removeSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
