/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;

/**
 *
 * @author kiran
 */
public class UserMovie{
    private Status status;
    private double rating;
    private Movie movie;
    
    public enum Status {
        WATCHED,
        WATCHING,
        PLAN_TO_WATCH,
        DROPPED
    }
    
    public UserMovie(Movie movie, double rating, Status staus){
        
        this.movie = movie;
        setStatus(status);
        setRating(rating);
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
}
