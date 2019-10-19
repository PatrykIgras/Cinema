package com.example.Cinema.repository;

import com.example.Cinema.domain.Movie;
import com.example.Cinema.domain.Poster;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PosterRepository extends CrudRepository<Poster, Long> {
    Optional<Poster> findByMovie(Movie movie);
}
