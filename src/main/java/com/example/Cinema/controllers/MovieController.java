package com.example.Cinema.controllers;

import com.example.Cinema.domain.EMovieCategory;
import com.example.Cinema.domain.Movie;
import com.example.Cinema.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/addMovie")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody Movie movie) {
        Long id = movieService.createMovie(movie.getTitle(), movie.getCategory(), movie.getLength(), movie.getDescription(), movie.getReuiredAge(), null);
        return movieService.getMovie(id).get();
    }

    @GetMapping("/getById")
    public Movie getMovie(@RequestParam("movieId") Long movieId) {
        return movieService.getMovie(movieId).get();
    }

    @GetMapping("/getInCategory")
    public Page<Movie> getMoviesInCategory(@RequestParam("category") EMovieCategory category) {
        return movieService.getMoviesInCategory(category, Pageable.unpaged());
    }

    @GetMapping("/getByPartOfTitle")
    public Page<Movie> getMoviesByPartOfTitle(@RequestParam String partOfTitle) {
        return movieService.getMoviesByPartOfTitle(partOfTitle, Pageable.unpaged());
    }

    @GetMapping("/getAll")
    public Page<Movie> getAllMovies() {
        return movieService.getAllMovies(Pageable.unpaged());
    }

    @PutMapping("/updateMovie")
    public Movie updateMovie(@RequestBody Movie movie) {
        movieService.updateMovie(movie.getId(), movie.getTitle(), movie.getCategory(), movie.getLength(), movie.getDescription(), movie.getReuiredAge(), null);
        return movieService.getMovie(movie.getId()).get();
    }

    @DeleteMapping("/deleteMovie")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeMovie(@RequestParam("movieId") Long movieId) {
        movieService.removeMovie(movieId);
    }
}
