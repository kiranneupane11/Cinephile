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
@Table(name = "user_movie_rating")
public class UserMovie extends BaseEntity{
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Status status;

    @Column(nullable = true)
    private double rating;

    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "movie_id", nullable = false)
    private Long movieId;
    
    public enum Status {
        Watched,
        Watching,
        Plan_To_Watch,
        Dropped
    }
    
    public UserMovie() {
    }
    
    public UserMovie(double rating, Status status){
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
