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
public class ViewMovieListsCommand implements Command {
    private final ConsoleIO io;
    private final UIHandler uiHandler;
    private final User loggedInUser;
    
    public ViewMovieListsCommand(ConsoleIO io, UIHandler uiHandler, User loggedInUser) {
        this.io = io;
        this.uiHandler = uiHandler;
        this.loggedInUser = loggedInUser;
    }
    
    @Override
    public void execute() {
        // Display available movie lists
        List<MovieList> lists = uiHandler.getUserLists(loggedInUser);
        if (lists.isEmpty()) {
            io.displayMessage("No movie lists found.");
            return;
        }
        for (MovieList list : lists) {
            io.displayMessage(list.getId() + ". " + list.getListName());
        }
        
        // Prompt for a list ID to view its movies
        long playlistId = io.readLong("Enter List ID to view it: ");
        List<UserMovieDTO> movies = uiHandler.getMoviesInList(playlistId, loggedInUser.getId());
        
        if (movies.isEmpty()) {
            io.displayMessage("No movies found in this list.");
            return;
        }
        
        // Display details for each movie in the selected list
        for (UserMovieDTO movie : movies) {
            io.displayMessage("ID: " + movie.getMovieID() +
                          " | Title: " + movie.getTitle() +
                          " | Year: " + movie.getReleaseYear() +
                          " | Genre: " + movie.getGenre() +
                          " | Rating: " + movie.getRating() +
                          " | Status: " + movie.getStatus()+
                          " | Description: " + movie.getDescription());
        }
    }
}
