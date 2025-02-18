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
@Table(name = "users")   
public class User {
    
    @Id
    private int userID;
    
    @Column(unique = true, nullable = false)
    protected String username;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapKey(name = "listName")
    private Map<String, MovieList> movieLists = new HashMap<>();

    public User() {} // Required for JPA
    
    public User(int userID, String username) {
        this.username = username;
        this.userID = userID; 
    }

    public String getUsername() {
        return username;
    }

    public int getUserID() {
        return userID;
    }

    public Map<String, MovieList> getMovieLists() {
        return Collections.unmodifiableMap(movieLists);
    }
    
    public boolean createMovieList(String listName) {
        if (movieLists.containsKey(listName)) {
            return false; 
        }
        movieLists.put(listName, new MovieList(listName));
        return true;
    }

    public void addMovieList(MovieList movieList) {
        movieLists.put(movieList.getListName(), movieList);
    }
}
