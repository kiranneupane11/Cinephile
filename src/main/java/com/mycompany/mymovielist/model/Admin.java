/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;

import com.mycompany.mymovielist.repository.*;
import java.util.*;

/**
 *
 * @author kiran
 */
public class Admin extends User {
    private final MovieRepository movieRepository;

    public Admin(int userID, String username, MovieRepository movieRepository) {
        super(userID, username); // Call the User constructor
        this.movieRepository = movieRepository;
    }

    public void addMovie(int id, String title, int year, String genre, double rating, String description) {
        Movie movie = new Movie(id, title, year, genre, rating, description);
        movieRepository.add(id, movie);
        System.out.println("Movie added: " + title);
    }

    public void removeMovie(int id) {
        movieRepository.remove(id);
        System.out.println("Movie removed with ID: " + id);
    }

    public Optional<Movie> getMovie(int id) {
        return movieRepository.get(id);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.getAll();
    }
}

