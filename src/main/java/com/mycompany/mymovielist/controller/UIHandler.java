/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.controller;

import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.Database.*;
import jakarta.persistence.EntityManager;
import java.util.*;
/**
 *
 * @author kiran
 */
public class UIHandler {
    private final DBUserRepository userRepository;
    private final DBMovieRepository movieRepository;
    private final DBUserMovieRepository userMovieRepository;
    private final DBMovieListRepository movieListRepository;
    
    public UIHandler(EntityManager entityManager) {
        this.userRepository = new DBUserRepository(entityManager);
        this.movieRepository = new DBMovieRepository(entityManager);
        this.userMovieRepository = new DBUserMovieRepository(entityManager);
        this.movieListRepository = new DBMovieListRepository(entityManager);
    }
    
    public Optional<User> login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt;
        }
        return Optional.empty();
    }
    
    public Optional<User> signup(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent()) {
            return Optional.empty(); 
        }
        User newUser = new User(username, email, password);
        userRepository.add(newUser.getId(), newUser);
        return Optional.of(newUser);
    }
    
    public List<Movie> getAvailableMovies() {
        return movieRepository.getAll();
    }

    public boolean addMovieToList(User user, long movieId, double rating, UserMovie.Status status) {
        Optional<Movie> movieOpt = movieRepository.get(movieId);
        if (movieOpt.isEmpty()) return false;
        
        String listName = status.name();
        MovieList createdList = user.createOrGetMovieList(listName);
        movieListRepository.add(createdList.getId(), createdList);

        UserMovie userMovie = new UserMovie(movieOpt.get(), createdList, rating, status);
        createdList.addMovie(userMovie);
        userMovieRepository.add(userMovie.getId(), userMovie);

        return true;
    }
    
    public List<MovieList> getUserLists(User user) {
        return movieListRepository.findByUser(user);
    }

    public Optional<MovieList> getMovieList(long listId) {
        return movieListRepository.get(listId);
    }

    public List<UserMovie> getMoviesInList(long listId) {
        return userMovieRepository.findByListID(listId);
    }
}
