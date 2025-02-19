package com.mycompany.mymovielist.model;

import java.time.Year; 
import jakarta.persistence.*;


/**
 *
 * @author kiran
 */

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "movie_id", nullable = false, updatable = false)
    private int movieID;

    @Column(name = "title", nullable = false, length = 75)
    private String title;

    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    @Column(name = "genre", nullable = false, length = 50)
    private String genre;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public Movie() {
    }

    public Movie(int movieID, String title, int releaseYear, String genre, double rating, String description) {
        setMovieID(movieID);
        setMovieTitle(title);
        setReleaseYear(releaseYear);
        setRating(rating);
        this.genre = genre;
        this.description = description;
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
    
}


