package com.mycompany.mymovielist.model;

import java.time.Year; 

/**
 *
 * @author kiran
 */
public class Movie {
    private int movieID;
    private String title;
    private int releaseYear;
    private String genre;
    private double rating;
    private String description;
    private Status status;
    
    public enum Status {
        WATCHED,
        WATCHING,
        PLAN_TO_WATCH
    }
    
    public Movie(int movieID, String title, int releaseYear, String genre, double rating, String description, Status status){
        setMovieID(movieID);
        setMovieTitle(title);
        setReleaseYear(releaseYear);
        this.genre = genre;
        setRating(rating);
        this.description = description;
        setStatus(status);
    }
    
    public int getMovieID(){
        return movieID;
        }

    public void setMovieID(int movieID){
        if (movieID <= 0) {
        throw new IllegalArgumentException("Movie ID must be greater than zero.");
    }
        this.movieID = movieID;
    }
    
    public double getRating(){
        return rating;
    }
    
    public void setRating(double rating){
        if(rating < 1 || rating > 10){
            throw new IllegalArgumentException("Rating must be between 1 and 10");
        }
        this.rating = Math.round(rating * 10) / 10.0 ;
    }
    
    public String getMovieTitle() {
        return title;
    }

    public void setMovieTitle(String title) {
        if (title.isEmpty() || title.length() > 75 ){
            throw new IllegalArgumentException("Title can't be empty or too long.");
        }
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        int currentYear = Year.now().getValue();
        if(releaseYear < 1900 || releaseYear > currentYear){
            throw new IllegalArgumentException("Release Year is out of range");
        }
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }
    
    public Status getStatus() {
        return status;
    }
}


