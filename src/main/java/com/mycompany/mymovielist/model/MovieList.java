/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;
import java.util.*;
import jakarta.persistence.*;


/**
 *
 * @author kiran
 */

@Entity
@Table(name = "user_playlist", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "name"}))
public class MovieList extends BaseEntity {
    
    @Column(name = "List_Name", nullable = false)
    private String listName;
    
    @Column(name = "user_id")
    private Long userId;

    public MovieList() {
    }
    
    public MovieList(String listName) { 
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<UserMovie> getMovies() {
        return userMovies;
    }

    public void addMovie(UserMovie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        userMovies.add(movie);
    }

    public void removeMovieById(int movieID) {
        userMovies.removeIf(userMovie -> userMovie.getMovie().getId() == movieID);
    }

    @Override
    public String toString() {
        return "MovieList{name='" + listName + "', totalMovies=" + userMovies.size() + "}";
    }
}