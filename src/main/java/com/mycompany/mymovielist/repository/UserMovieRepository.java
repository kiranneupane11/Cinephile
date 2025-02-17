/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.repository;
import com.mycompany.mymovielist.model.*;

/**
 *
 * @author kiran
 */
public class UserMovieRepository extends AbstractRepository<UserMovie, String> {
    
    public void updateRating(String movieId, double newRating) {
        UserMovie userMovie = items.get(movieId);
        if (userMovie != null) {
            userMovie.setRating(newRating);
        } else {
            throw new IllegalArgumentException("UserMovie with given movie ID not found.");
        }
    }
    
    public void updateStatus(String movieId, UserMovie.Status newStatus) {
        UserMovie userMovie = items.get(movieId);
        if (userMovie != null) {
            userMovie.setStatus(newStatus);
        } else {
            throw new IllegalArgumentException("UserMovie with given movie ID not found.");
        }
    }
}
