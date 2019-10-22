package com.example.Cinema.dto_models;

import com.example.Cinema.domain.EMovieCategory;

public class MovieDTO {
    private String title;
    private EMovieCategory category;
    private Integer length;
    private String description;
    private Integer requiredAge;
    private String posterFilePath;

    public MovieDTO() {
    }

    public MovieDTO(String title, EMovieCategory category, Integer length, String description, Integer requiredAge, String posterFilePath) {
        this.title = title;
        this.category = category;
        this.length = length;
        this.description = description;
        this.requiredAge = requiredAge;
        this.posterFilePath = posterFilePath;
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

    public Integer getRequiredAge() {
        return requiredAge;
    }

    public void setRequiredAge(Integer requiredAge) {
        this.requiredAge = requiredAge;
    }

    public String getPosterFilePath() {
        return posterFilePath;
    }

    public void setPosterFilePath(String posterFilePath) {
        this.posterFilePath = posterFilePath;
    }
}
