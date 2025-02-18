/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;

import java.util.*;

/**
 *
 * @author kiran
 */
public class User {
    protected String username;
    private final int userID;
    private final Map<String, MovieList> movieLists;

    public User(int userID, String username) {
        this.username = username;
        this.userID = new Random().nextInt(1000); // Assign random user ID
        this.movieLists = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public int getUserID() {
        return userID;
    }

    public Map<String, MovieList> getMovieLists() {
        return Collections.unmodifiableMap(movieLists);
    }
    
    public boolean createMovieList(String listName) {
        if (movieLists.containsKey(listName)) {
            return false; 
        }
        movieLists.put(listName, new MovieList(listName));
        return true;
    }

    public void addMovieList(MovieList movieList) {
        movieLists.put(movieList.getListName(), movieList);
    }
}
