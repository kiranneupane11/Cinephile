/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist;
import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kiran
 */
class AbstractRepositoryTest {

    private MovieRepository movieRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        movieRepository = new MovieRepository();
        userRepository = new UserRepository();
    }

    @Test
    void testAddAndRetrieveMovie() {
        Movie movie = new Movie(1, "Inception", 2010, "Sci-Fi", 8.8, "A mind-bending thriller");
        movieRepository.add(1, movie);

        Optional<Movie> retrievedMovie = movieRepository.get(1);
        assertTrue(retrievedMovie.isPresent());
        assertEquals("Inception", retrievedMovie.get().getMovieTitle());
    }

    @Test
    void testRemoveMovie() {
        Movie movie = new Movie(1, "Inception", 2010, "Sci-Fi", 8.8, "A mind-bending thriller");
        movieRepository.add(1, movie);
        movieRepository.remove(1);

        Optional<Movie> retrievedMovie = movieRepository.get(1);
        assertFalse(retrievedMovie.isPresent()); // Should be empty
    }

    @Test
    void testGetAllMovies() {
        movieRepository.add(1, new Movie(1, "Inception", 2010, "Sci-Fi", 8.8, "A mind-bending thriller"));
        movieRepository.add(2, new Movie(2, "The Dark Knight", 2008, "Action", 9.0, "Batman fights Joker"));

        List<Movie> allMovies = movieRepository.getAll();
        assertEquals(2, allMovies.size());
    }

    @Test
    void testAddAndRetrieveUser() {
        User user = new User(1001, "JohnDoe");
        userRepository.add(1001, user);

        Optional<User> retrievedUser = userRepository.get(1001);
        assertTrue(retrievedUser.isPresent());
        assertEquals("JohnDoe", retrievedUser.get().getUsername());
    }
}
