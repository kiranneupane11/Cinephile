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
    private int userID;
    private Map<String, MovieList> movieLists;
    
    public User(String username) {
        this.username = username;
        this.userID = userID;
        this.movieLists = new HashMap<>();
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getUserID() {
        return userID;
    }
    
   
    public void createList(String listName) {
        if (movieLists.containsKey(listName)) {
            System.out.println("List '" + listName + "' already exists.");
            return;
        }
        movieLists.put(listName, new MovieList(listName));
        System.out.println(username + " created a new movie list: " + listName);
    }

    // ✅ Add a movie to a specific list
    
    public void addToList(String listName, Movie movie) {
        if (!movieLists.containsKey(listName)) {
            System.out.println("List '" + listName + "' does not exist.");
            return;
        }
        movieLists.get(listName).addMovie(movie);
        System.out.println("Added '" + movie.getMovieTitle() + "' to " + listName);
    }

    // ✅ Get a specific list of movies
    
    public void viewThisList(String listName) {
        
        if (!movieLists.containsKey(listName)) {
            System.out.println("List '" + listName + "' does not exist.");
        }
        
        List<Movie> movies = movieLists.get(listName).getMovies();
       
        for (Movie movie : movies) {
            System.out.println("- " + movie.getMovieTitle());
        }   
    }

    // ✅ View all lists owned by the user
   
    public void viewAllLists() {
        if (movieLists.isEmpty()) {
            System.out.println(username + " has no movie lists.");
            return;
        }
        System.out.println("\n" + username + "'s Movie Lists:");
        for (String listName : movieLists.keySet()) {
            System.out.println("- " + listName);
        }
    }

    // ✅ View a specific list from another user
    
//    public void viewOtherUsersList(User user, String listName) {
//        List<Movie> movies = user.getList(listName);
//        if (movies == null || movies.isEmpty()) {
//            System.out.println(user.getUsername() + "'s list '" + listName + "' is empty or does not exist.");
//            return;
//        }
//        System.out.println("\n" + user.getUsername() + "'s Movie List: " + listName);
//        for (Movie movie : movies) {
//            movie.getMovieTitle();
//        }
//    }
}

