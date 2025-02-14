/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;

import com.mycompany.mymovielist.model.Movie;

/**
 *
 * @author kiran
 */
public class UserMovie extends Movie{
    private Status status;
    private double rating;
    
    public enum Status {
        WATCHED,
        WATCHING,
        PLAN_TO_WATCH,
        DROPPED
    }
    
    public UserMovie(Movie movie, double rating, Status staus){
        
        super(movie.getMovieID(), movie.getMovieTitle(), movie.getReleaseYear(), movie.getGenre(), movie.getDescription());
        setStatus(status);
        setRating(rating);
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
}
