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
        List<UserPlaylist> lists = uiHandler.getUserLists(loggedInUser);
        if (lists.isEmpty()) {
            io.displayMessage("No movie lists found.");
            return;
        }
        for (UserPlaylist list : lists) {
            io.displayMessage(list.getId() + ". " + list.getListName());
        }
        
        long playlistId = io.readLong("Enter List ID to view it: ");
        List<UserMovieRatingDTO> movies = uiHandler.getMoviesInList(playlistId, loggedInUser.getId());
        
        if (movies.isEmpty()) {
            io.displayMessage("No movies found in this list.");
            return;
        }
        
        for (UserMovieRatingDTO movie : movies) {
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
