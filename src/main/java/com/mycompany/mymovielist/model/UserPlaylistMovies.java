/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;
import jakarta.persistence.*;


/**
 *
 * @author kiran
 */
@Entity
@Table(name = "user_playlist_movies", uniqueConstraints = @UniqueConstraint(columnNames = {"list_id", "movie_id"}))
public class UserPlaylistMovies extends BaseEntity {
    
    @ManyToOne
    @JoinColumn(name = "user_playlist_id", nullable = false)
    private UserPlaylist userPlaylistId;
    
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movieId;
    
    public UserPlaylistMovies() {}
    
    public UserPlaylistMovies(UserPlaylist userPlaylistId, Movie movieId) {
        this.userPlaylistId = userPlaylistId;
        this.movieId = movieId;
    }
    
    public UserPlaylist getUserPlaylistId(){
        return userPlaylistId;
    }
    
    public Movie getMovieId(){
        return movieId;
    }
   
    
}

