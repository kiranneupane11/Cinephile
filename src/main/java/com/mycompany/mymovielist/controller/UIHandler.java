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
    private final DBUserPlaylistMoviesRepository userPlayListMoviesRepository;
    
    public UIHandler(EntityManager entityManager) {
        this.userRepository = new DBUserRepository(entityManager);
        this.movieRepository = new DBMovieRepository(entityManager);
        this.userMovieRepository = new DBUserMovieRepository(entityManager);
        this.movieListRepository = new DBMovieListRepository(entityManager);
        this.userPlayListMoviesRepository = new DBUserPlaylistMoviesRepository(entityManager);
    }
    
    public Optional<User> login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && PasswordUtil.verifyPassword(password, userOpt.get().getPassword())) {
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

    public boolean addMovieToList(User user, UserMovie userMovie, MovieList movieList) {
        Optional<Movie> movieOpt = movieRepository.get(userMovie.getMovieID());
        if (movieOpt.isEmpty()) return false;
        
       //*** ASK TO ADD MOVIE TO STATUS LIST OR CUSTOM LIST ***
       
        Optional<MovieList> existingListOpt = movieListRepository.findByUserIdAndListName(movieList);
        
            if (existingListOpt.isPresent()) {
             movieList = existingListOpt.get();
            } else {
            // Create new list if not found
             movieList = new MovieList(movieList.getListName(), user.getId());
            movieListRepository.add(movieList.getId(), movieList);
            }
            
        UserPlaylistMovies userPlaylistMovie = new UserPlaylistMovies(movieList.getId(), userMovie.getMovieID());
        userPlayListMoviesRepository.add(userPlaylistMovie.getId(), userPlaylistMovie);
        userMovieRepository.add(userMovie.getId(), userMovie);

        return true;
    }
    
    public List<MovieList> getUserLists(User user) {
        return movieListRepository.getListsByUserId(user);
    }

    public List<UserMovieDTO> getMoviesInList(Long playlistId, Long userId) {
        List<Object[]> results = userPlayListMoviesRepository.getMoviesFromPlaylist(playlistId, userId);

        List<UserMovieDTO> movies = new ArrayList<>();
        for (Object[] row : results) {
            UserMovie userMovie = (UserMovie) row[0];
            Movie movie = (Movie) row[1];
            movies.add(new UserMovieDTO(movie, userMovie));
    }
    return movies;
}
}
