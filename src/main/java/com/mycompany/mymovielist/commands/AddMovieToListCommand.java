/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.commands;
import com.mycompany.mymovielist.view.*;
import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.controller.*;
import java.util.*;
/**
 *
 * @author kiran
 */
public class AddMovieToListCommand implements Command{
    private final ConsoleIO io;
    private final UIHandler uiHandler;
    private final User loggedInUser;
    
    public AddMovieToListCommand(ConsoleIO io, UIHandler uiHandler, User loggedInUser) {
        this.io = io;
        this.uiHandler = uiHandler;
        this.loggedInUser = loggedInUser;
    }
    
    @Override
    public void execute() {
        // Display available movies
        List<Movie> movies = uiHandler.getAvailableMovies();
        if (movies.isEmpty()) {
            io.displayMessage("No movies available.");
            return;
        }
        for (Movie movie : movies) {
            io.displayMessage(movie.getId() + ". " + movie.getTitle());
        }
        long movieId = io.readLong("Enter movie ID: ");
        double rating = Double.parseDouble(io.readString("Enter your rating: "));
        
        io.displayMessage("Select status:");
        UserMovie.Status[] statuses = UserMovie.Status.values();
        for (int i = 0; i < statuses.length; i++) {
            io.displayMessage((i + 1) + ". " + statuses[i]);
        }
        int statusChoice = io.readInt("Enter choice: ");
        UserMovie.Status status = statuses[statusChoice - 1];
        
        UserMovie userMovie = new UserMovie(movieId,rating, status, loggedInUser.getId());
        String listName = io.readString("Enter List Name to add the movie");
        MovieList newMovieList = new MovieList(listName, loggedInUser.getId());
        
        if (uiHandler.addMovieToList(loggedInUser, userMovie, newMovieList)) {
            io.displayMessage("Movie added successfully!");
        } else {
            io.displayMessage("Invalid movie ID.");
        }
    }
}
