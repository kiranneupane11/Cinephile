/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;
import java.util.*;
import java.util.stream.Collectors;


/**
 *
 * @author kiran
 */
public class MovieList {
    private String listName;
    private List<Movie> movies;

    public MovieList(String listName) {
        this.listName = listName;
        this.movies = new ArrayList<>();
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<Movie> getMovies() {
        return new ArrayList<>(movies);
    }

    public void addMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        movies.add(movie);
    }

    public void removeMovieByTitle(String title) {
        movies.removeIf(movie -> movie.getMovieTitle() == title);
    }

    public void removeMovieById(int movieID) {
        movies.removeIf(movie -> movie.getMovieID() == movieID);
    }

    public boolean containsMovie(Movie movie) {
        return movies.contains(movie);
    }

    public int getTotalMovies() {
        return movies.size();
    }

    // Sorting methods
    public List<Movie> sortByTitle() {
        return movies.stream()
                .sorted(Comparator.comparing(Movie::getMovieTitle))
                .collect(Collectors.toList());
    }

    public List<Movie> sortByReleaseYear() {
        return movies.stream()
                .sorted(Comparator.comparingInt(Movie::getReleaseYear))
                .collect(Collectors.toList());
    }

    public List<Movie> sortByRatingDescending(){
        return movies.stream()
                .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                .collect(Collectors.toList());
    }

    // Filtering methods
    public List<Movie> filterByGenre(String genre) {
        return movies.stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByMinimumRating(double minRating) {
        return movies.stream()
                .filter(movie -> movie.getRating() >= minRating)
                .collect(Collectors.toList());
    }
    
    public List<Movie> filterByMaxRating(double maxRating){
        return movies.stream()
                .filter(movie -> movie.getRating() >= maxRating)
                .collect(Collectors.toList());
    }

    public List<Movie> filterByReleaseYearRange(int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "MovieList{name='" + listName + "', totalMovies=" + movies.size() + "}";
    }
}