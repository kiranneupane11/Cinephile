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
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final UserMovieRatingRepository userMovieRepository;
    private final UserPlaylistRepository userPlaylistRepository;
    private final UserPlaylistMoviesRepository userPlayListMoviesRepository;
    private final PasswordService passwordService;
    
    public UIHandler(EntityManager entityManager, PasswordService passwordService) {
        this.userRepository = new UserRepository(entityManager);
        this.movieRepository = new MovieRepository(entityManager);
        this.userMovieRepository = new UserMovieRatingRepository(entityManager);
        this.userPlaylistRepository = new UserPlaylistRepository(entityManager);
        this.userPlayListMoviesRepository = new UserPlaylistMoviesRepository(entityManager);
        this.passwordService = new BasicPasswordService();
    }
    
    public Optional<User> login(String usernameOrEmail, String password) {
        Optional<User> userOpt = userRepository.findByUsername(usernameOrEmail);
        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByEmail(usernameOrEmail);
        }
        if (userOpt.isPresent() && passwordService.verifyPassword(password, userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }
    
    public Optional<User> signup(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent()) {
            return Optional.empty(); 
        }
        userRepository.add(user.getId(), user);
        return Optional.of(user);
    }
    
    public List<Movie> getAvailableMovies() {
        return movieRepository.getAll();
    }

    public boolean addMovieToList(User user, UserMovieRating userMovieRating, UserPlaylist userPlaylist) {
        Optional<Movie> movieOpt = movieRepository.get(userMovieRating.getMovieID().getId());
        if (movieOpt.isEmpty()) return false;
               
        Optional<UserPlaylist> existingListOpt = userPlaylistRepository.findByUserIdAndListName(userPlaylist);
        
            if (existingListOpt.isPresent()) {
             userPlaylist = existingListOpt.get();
            } else {
            userPlaylist = new UserPlaylist(userPlaylist.getListName(), userPlaylist.getUserID());
            userPlaylistRepository.add(userPlaylist.getId(), userPlaylist);
            }
            
        UserPlaylistMovies userPlaylistMovie = new UserPlaylistMovies(userPlaylist, userMovieRating.getMovieID());
        userPlayListMoviesRepository.add(userPlaylistMovie.getId(), userPlaylistMovie);
        userMovieRepository.add(userMovieRating.getId(), userMovieRating);

        return true;
    }
    
    public List<UserPlaylist> getUserLists(User user) {
        return userPlaylistRepository.getListsByUserId(user);
    }

    public List<UserMovieRatingDTO> getMoviesInList(UserPlaylist playlist, User user) {
    return userPlayListMoviesRepository.getMoviesFromPlaylist(playlist, user);
}
    
    public Optional<Movie> getMovieById(long Id){
        return movieRepository.get(Id);
    }
    
    public Optional<UserPlaylist> getUserPlaylistById(long playlistId, User user) {
    return userPlaylistRepository.get(playlistId)
        .filter(playlist -> playlist.getUserID().equals(user)); 
}
}
