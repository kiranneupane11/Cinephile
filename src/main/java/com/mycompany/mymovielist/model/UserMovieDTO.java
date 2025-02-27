/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;
import java.time.Year; 


/**
 *
 * @author kiran
 */
public class UserMovieDTO {
    private String title;
    private String genre;
    private double rating;
    private UserMovie.Status status;
    private Long movieID;
    private Year releaseYear;
    private String description;
    

    public UserMovieDTO(Movie movie, UserMovie userMovie) {
        this.movieID = movie.getId();
        this.title = movie.getTitle();
        this.releaseYear = movie.getReleaseYear();
        this.genre = movie.getGenre();
        this.rating = userMovie.getRating();
        this.status = userMovie.getStatus();
        this.description = movie.getDescription();
    }

    public Long getMovieID() { return movieID; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public double getRating() { return rating; }
    public UserMovie.Status getStatus() { return status; }
    public Year getReleaseYear() {return releaseYear; }
    public String getDescription(){return description; }
}