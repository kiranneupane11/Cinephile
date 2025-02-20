/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.service;

import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.repository.*;
import com.mycompany.mymovielist.Database.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author kiran
 */
//public class UserListManager {
//    private final DBUserRepository userRepository;
//
//    public UserListManager(DBUserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    // Retrieve a specific user's movie lists
//    public Optional<MovieList> getMovieList(int userID, String listName) {
//        return userRepository.get(userID).map(user -> user.getMovieLists().get(listName));
//    }
//
//    // Add a movie to a specific list
//    public void addMovieToList(int userID, String listName, UserMovie userMovie) {
//        getMovieList(userID, listName)
//                .ifPresentOrElse(
//                        list -> list.addMovie(userMovie),
//                        () -> { throw new IllegalArgumentException("List not found"); }
//                );
//    }
//
//    // Sort movies in a user's list by title
//    public List<UserMovie> sortMoviesByTitle(int userID, String listName) {
//        return getMovieList(userID, listName)
//                .map(list -> list.getMovies().stream()
//                        .sorted(Comparator.comparing(m -> m.getMovie().getMovieTitle()))
//                        .collect(Collectors.toList()))
//                .orElse(Collections.emptyList());
//    }
//
//    // Filter movies by rating
//    public List<UserMovie> filterByRating(int userID, String listName, double minRating) {
//        return getMovieList(userID, listName)
//                .map(list -> list.getMovies().stream()
//                        .filter(m -> m.getRating() >= minRating)
//                        .collect(Collectors.toList()))
//                .orElse(Collections.emptyList());
//    }
//}
//
