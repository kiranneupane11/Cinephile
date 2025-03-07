/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.commands;
import java.util.*;
import com.mycompany.mymovielist.view.*;
import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.controller.*;


/**
 *
 * @author kiran
 */
public class ViewAvailableMoviesCommand implements Command{
    private final ConsoleIO io;
    private final UIHandler uiHandler;
    
    public ViewAvailableMoviesCommand(ConsoleIO io, UIHandler uiHandler) {
        this.io = io;
        this.uiHandler = uiHandler;
    }
    
    @Override
    public void execute() {
        List<Movie> movies = uiHandler.getAvailableMovies();
        if (movies.isEmpty()) {
            io.displayMessage("No movies available.");
        } else {
            for (Movie movie : movies) {
                io.displayMessage(movie.getId() + ". " + movie.getTitle());
            }
        }
    }
}
