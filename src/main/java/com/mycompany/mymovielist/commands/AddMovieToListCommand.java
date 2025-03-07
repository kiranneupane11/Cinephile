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
        List<Movie> movies = uiHandler.getAvailableMovies();
        if (movies.isEmpty()) {
            io.displayMessage("No movies available.");
            return;
        }
        for (Movie movie : movies) {
            io.displayMessage(movie.getId() + ". " + movie.getTitle());
        }
        long Id = io.readLong("Enter movie ID: ");
        Optional<Movie> movieOpt = uiHandler.getMovieById(Id);
        
        if (movieOpt.isEmpty()) {
            io.displayMessage("Invalid movie ID.");
            return;
        }
        
        Double rating = null;
        String ratingInput = io.readString("Enter your rating (or press Enter to skip): ").trim();
        if (!ratingInput.isEmpty()) {
            try {
                rating = Double.parseDouble(ratingInput);
            } catch (NumberFormatException e) {
                io.displayMessage("Invalid rating input. Skipping rating.");
            }
        }  
        
        io.displayMessage("Select status:");
        UserMovieRating.Status[] statuses = UserMovieRating.Status.values();
        for (int i = 0; i < statuses.length; i++) {
            io.displayMessage((i + 1) + ". " + statuses[i]);
        }
        int statusChoice = io.readInt("Enter choice: ");
        UserMovieRating.Status status = statuses[statusChoice - 1];
        
        UserMovieRating userMovieRating = new UserMovieRating(movieOpt.get(),rating, status, loggedInUser);
        String listName = io.readString("Enter List Name to add the movie");
        UserPlaylist newMovieList = new UserPlaylist(listName, loggedInUser);
        
        if (uiHandler.addMovieToList(loggedInUser, userMovieRating, newMovieList)) {
            io.displayMessage("Movie added successfully!");
        } else {
            io.displayMessage("Invalid movie ID.");
        }
    }
}
