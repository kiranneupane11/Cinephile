/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;

import com.mycompany.mymovielist.Database.*;
import java.util.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import java.time.Year; 



/**
 *
 * @author kiran
 */
//@Entity
//public class Admin extends User {
//
//    @Transient // Prevents Hibernate from persisting this field
//    private final DBMovieRepository movieRepository;
//
//    public Admin(long userID, String username, String email, String password, DBMovieRepository movieRepository) {
//        super(userID, username, email, password);
//        this.movieRepository = movieRepository;
//    }
//
//    public void addMovie(long id, String title, Year year, String genre, double rating, String description) {
//        Movie movie = new Movie(id, title, year, genre, rating, description);
//        movieRepository.add(id, movie);
//        System.out.println("Movie added: " + title);
//    }
//
//    public void removeMovie(int id) {
//        movieRepository.remove(id);
//        System.out.println("Movie removed with ID: " + id);
//    }
//
//    public Optional<Movie> getMovie(int id) {
//        return movieRepository.get(id);
//    }
//
//    public List<Movie> getAllMovies() {
//        return movieRepository.getAll();
//    }
//}
