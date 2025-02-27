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
    
    @Column(name = "user_playlist_id", nullable = false)
    private Long userPlaylistId;
    
    @Column(name = "movie_id", nullable = false)
    private Long movieId;
    
    public UserPlaylistMovies() {}
    
    public UserPlaylistMovies(Long listId, Long movieId) {
        this.userPlaylistId = listId;
        this.movieId = movieId;
    }
    
    public long getUserPlaylistId(){
        return userPlaylistId;
    }
    
    public long getMovieId(){
        return movieId;
    }
   
    
}

