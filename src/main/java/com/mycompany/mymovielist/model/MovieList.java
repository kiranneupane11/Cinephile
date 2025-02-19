/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;
import java.util.*;
import java.util.stream.Collectors;
import jakarta.persistence.*;


/**
 *
 * @author kiran
 */

@Entity
@Table(name = "movie_lists")
public class MovieList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int listID;
    
    @Column(name = "list_name", nullable = false, unique = true)
    private String listName;
    
    @OneToMany(mappedBy = "movieList", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserMovie> movies = new ArrayList<>();

    public MovieList() {
    }
    
    public MovieList(String listName) { 
        this.listName = listName;
    }
    
    public int getListID(){
        return listID;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<UserMovie> getMovies() {
        return new ArrayList<>(movies);
    }

    public void addMovie(UserMovie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        movies.add(movie);
    }

    public void removeMovieByTitle(String title) {
        movies.removeIf(userMovie -> userMovie.getMovie().getMovieTitle().equals(title));
    }

    public void removeMovieById(int movieID) {
        movies.removeIf(userMovie -> userMovie.getMovie().getMovieID() == movieID);
    }

    public boolean containsMovie(UserMovie movie) {
        return movies.contains(movie);
    }

    public int getTotalMovies() {
        return movies.size();
    }


    @Override
    public String toString() {
        return "MovieList{name='" + listName + "', totalMovies=" + movies.size() + "}";
    }
}