package com.example.Cinema.controllers;

import com.example.Cinema.domain.Marathon;
import com.example.Cinema.domain.Movie;
import com.example.Cinema.services.MarathonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/marathon")
public class MarathonController {

    private MarathonService marathonService;

    public MarathonController(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @PostMapping("/addMarathon")
    public Marathon createMarathon(@RequestBody Marathon marathon) {
        List<Long> movieIds = new ArrayList<>();
        for (Movie movie : marathon.getMovies()) {
            movieIds.add(movie.getId());
        }
        Long marathonId = marathonService.createMarathon(marathon.getName(), marathon.getStartTime(), movieIds);
        return marathonService.getMarathon(marathonId).get();
    }

    @GetMapping("/getById")
    public Marathon getMarathon(@RequestParam("marathonId") Long marathonId) {
        return marathonService.getMarathon(marathonId).get();
    }

    @GetMapping("/getAll")
    public List<Marathon> getAllMarathons() {
        return marathonService.getAllMarathons();
    }

    @DeleteMapping("/deleteById")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeMarathon(@RequestParam("marathonId") Long marathonId) {
        marathonService.removeMarathon(marathonId);
    }
}
