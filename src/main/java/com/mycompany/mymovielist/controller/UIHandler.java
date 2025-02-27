/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.controller;

import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.Database.*;
import jakarta.persistence.EntityManager;
import com.mycompany.mymovielist.util.*;
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
    
    public Optional<User> login(User user) {
        Optional<User> userOpt = userRepository.findByUsername(user.getUsername());
        if (userOpt.isPresent() && PasswordUtil.verifyPassword(user.getPassword(), userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }
    
    public Optional<User> signup(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent()) {
            return Optional.empty(); 
        }
        User newUser = new User(user.getUsername(), user.getEmail(), user.getPassword());
        userRepository.add(newUser.getId(), newUser);
        return Optional.of(newUser);
    }
    
    public List<Movie> getAvailableMovies() {
        return movieRepository.getAll();
    }

    public boolean addMovieToList(User user, UserMovie usermovie) {
        Optional<Movie> movieOpt = movieRepository.get(usermovie.getMovieID());
        if (movieOpt.isEmpty()) return false;
        
       //*** ASK TO ADD MOVIE TO STATUS LIST OR CUSTOM LIST ***
       
        String listName = usermovie.getStatus().name();
        MovieList createdList = user.createOrGetMovieList(listName);
        movieListRepository.add(createdList.getId(), createdList);

        UserMovie userMovie = new UserMovie(movieOpt.get(), createdList, rating, status);
        createdList.addMovie(userMovie);
        userMovieRepository.add(userMovie.getId(), userMovie);

        return true;
    }
    
    public List<MovieList> getUserLists(User user) {
        return movieListRepository.getListByUserId(user.getId());
    }

    public Optional<MovieList> getMovieList(long listId) {
        return movieListRepository.get(listId);
    }

    public List<UserMovie> getMoviesInList(long listId) {
        return userMovieRepository.findByListID(listId);
    }
}
