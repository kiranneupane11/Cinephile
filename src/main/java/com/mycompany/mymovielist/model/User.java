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
@Table(name = "User")   
public class User {
    
    @Id
    private long userID;
    
    @Column(unique = true, nullable = false)
    protected String username;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
        
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapKey(name = "listName")
    private Map<String, MovieList> movieLists = new HashMap<>();

    public User() {} 
    
    public User(int userID, String username, String email, String password) {
        this.username = username;
        this.userID = userID;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public long getUserID() {
        return userID;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
