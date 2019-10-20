package com.example.Cinema.services;

import com.example.Cinema.domain.Marathon;
import com.example.Cinema.domain.Movie;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MarathonService {
    Long createMarathon(String name, LocalDateTime startTime, List<Long> movieIds);

    Optional<Marathon> getMarathon(Long marathonId);

    List<Marathon> getAllMarathons();

    void removeMarathon(Long marathonId);
}
