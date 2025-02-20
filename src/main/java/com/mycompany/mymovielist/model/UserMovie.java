/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;

import jakarta.persistence.*;

/**
 *
 * @author kiran
 */

@Entity
@Table(name = "UserMovie")
public class UserMovie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Status status;

    @Column(nullable = true)
    private double rating;

    @ManyToOne
    @JoinColumn(name = "movieID", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "listID", nullable = false)
    private MovieList movieList;
    
    public enum Status {
        Watched,
        Watching,
        Plan_To_Watch,
        Dropped
    }
    
    public UserMovie() {
    }
    
    public UserMovie(Movie movie, MovieList movieList, double rating, Status status){
        
        this.id = id;
        this.movie = movie;
        this.movieList = movieList;
        setStatus(status);
        setRating(rating);
    }
    
    public long getId(){
        return id;
    }
    
    public Movie getMovie(){
        return movie;
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
    
     public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }
    
    public Status getStatus() {
        return status;
    }
    @Override
    public String toString() {
    return "UserMovie{" +
           "movie=" + movie.getMovieTitle() +
           ", rating=" + rating +
           ", status=" + status +
           '}';
}
}
