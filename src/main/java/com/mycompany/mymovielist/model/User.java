/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;

import com.mycompany.mymovielist.model.UserMovie.Status;

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
        this.userID = new Random().nextInt(1000); // Assign random user ID for uniqueness
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

    // ✅ Add a UserMovie to a specific list
    public void addToList(String listName, Movie movie) {
    if (!movieLists.containsKey(listName)) {
        System.out.println("List '" + listName + "' does not exist.");
        return;
    }

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your rating (1-10) for '" + movie.getMovieTitle() + "': ");
    double rating = scanner.nextDouble();
    scanner.nextLine();  

    System.out.print("Enter movie status ('Watched', 'Plan to Watch', 'Watching', 'Dropped'): ");
    String statusInput = scanner.nextLine().trim();

    UserMovie userMovie = null; 

    try {
        Status status = Status.valueOf(statusInput);
        userMovie = new UserMovie(movie, rating, status);
        movieLists.get(listName).addMovie(userMovie);
        System.out.println("Added '" + userMovie.getMovie().getMovieTitle() + "' to " + listName);
    } catch (IllegalArgumentException e) {
        System.out.println("Invalid status entered. Please enter one of: Watched, Watching, Plan_to_Watch, Dropped.");
    }
}


    // ✅ View a specific movie list
    public void viewThisList(String listName) {
        if (!movieLists.containsKey(listName)) {
            System.out.println("List '" + listName + "' does not exist.");
            return;
        }
        
        List<UserMovie> movies = movieLists.get(listName).getMovies();
        
        System.out.println("\nMovies in " + listName + ":");
        for (UserMovie userMovie : movies) {
            System.out.println("- " + userMovie.getMovie().getMovieTitle() + 
                               " (Rating: " + userMovie.getRating() + 
                               ", Status: " + userMovie.getStatus() + ")");
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
//}

