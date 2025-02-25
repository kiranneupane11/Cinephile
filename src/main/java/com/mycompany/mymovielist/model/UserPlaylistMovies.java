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
@Table(name = "MovieListItem", uniqueConstraints = @UniqueConstraint(columnNames = {"list_id", "movie_id"}))
public class UserPlaylistMovies extends BaseEntity {
    
    @Column(name = "list_id", nullable = false)
    private Long listId;
    
    @Column(name = "movie_id", nullable = false)
    private Long movieId;
    
    public UserPlaylistMovies() {}
    
    public UserPlaylistMovies(Long listId, Long movieId) {
        this.listId = listId;
        this.movieId = movieId;
    }
    
}

