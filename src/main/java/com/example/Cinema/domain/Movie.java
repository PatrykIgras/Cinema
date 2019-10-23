package com.example.Cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "movie_id")
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private EMovieCategory category;
    private Integer length;
    private String description;
    @Column(name = "required_age")
    private Integer requiredAge;

    public Movie() {
    }

    public Movie(Long id, String title, EMovieCategory category, Integer length, String description, Integer reuiredAge) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.length = length;
        this.description = description;
        this.requiredAge = reuiredAge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EMovieCategory getCategory() {
        return category;
    }

    public void setCategory(EMovieCategory category) {
        this.category = category;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReuiredAge() {
        return requiredAge;
    }

    public void setReuiredAge(Integer reuiredAge) {
        this.requiredAge = reuiredAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        return id != null && id.equals(movie.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", length=" + length +
                ", description='" + description + '\'' +
                ", reuiredAge=" + requiredAge +
                '}';
    }

    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.PERSIST ,CascadeType.MERGE})
    @JsonIgnore
    private List<Marathon> marathons;

    public List<Marathon> getMarathons() {
        if (marathons == null) marathons = new ArrayList<>();
        return marathons;
    }

    public void setMarathons(List<Marathon> marathons) {
        this.marathons = marathons;
    }
}
