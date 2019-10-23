package com.example.Cinema.controllers;

import com.example.Cinema.domain.Marathon;
import com.example.Cinema.domain.Movie;
import com.example.Cinema.services.MarathonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marathon")
public class MarathonController {

    private MarathonService marathonService;

    public MarathonController(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @PostMapping("/addMarathon")
    public Marathon createMarathon(@RequestBody Marathon marathon){
        List<Long> movieIds = new ArrayList<>();
        for (Movie movie : marathon.getMovies()){
            movieIds.add(movie.getId());
        }
        Long marathonId = marathonService.createMarathon(marathon.getName(), marathon.getStartTime(), movieIds);
        return marathonService.getMarathon(marathonId).get();
    }
//
//    Optional<Marathon> getMarathon(Long marathonId);
//
//    List<Marathon> getAllMarathons();
//
//    void removeMarathon(Long marathonId);
}
