package com.mycompany.mymovielist.model;

import java.time.Year; 
import jakarta.persistence.*;



/**
 *
 * @author kiran
 */

@Entity
@Table(name = "movie")
public class Movie extends BaseEntity{

    @Column(name = "title", nullable = false, length = 75)
    private String title;

    @Column(name = "release_year", nullable = false)
    private Year releaseYear;

    @Column(name = "genre", nullable = false, length = 75)
    private String genre;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public Movie() {
    }

    public Movie(String title, Year releaseYear, String genre, double rating, String description) {
        setMovieTitle(title);
        setReleaseYear(releaseYear);
        setRating(rating);
        this.genre = genre;
        this.description = description;
    }
    
    public double getRating(){
        return rating;
    }
    
    public void setRating(double rating){
        this.rating = rating;
    }
    
    
    public String getTitle() {
        return title;
    }

    public void setMovieTitle(String title) {
        this.title = title;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
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


