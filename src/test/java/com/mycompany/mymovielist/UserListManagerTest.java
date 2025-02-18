/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.repository.*;
import com.mycompany.mymovielist.service.*;

/**
 *
 * @author kiran
 */

import static org.junit.jupiter.api.Assertions.*;

class UserListManagerTest {

    private UserRepository userRepository;
    private UserListManager userListManager;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userListManager = new UserListManager(userRepository);
    }

    @Test
    void testCreateAndRetrieveMovieList() {
        User user = new User(1001, "JohnDoe");
        userRepository.add(1001, user);

        user.createMovieList("Favorites");
        Optional<MovieList> retrievedList = userListManager.getMovieList(1001, "Favorites");

        assertTrue(retrievedList.isPresent());
        assertEquals("Favorites", retrievedList.get().getListName());
    }

    @Test
    void testAddMovieToList() {
        User user = new User(1001, "JohnDoe");
        userRepository.add(1001, user);

        user.createMovieList("Sci-Fi Movies");
        Movie movie = new Movie(1, "Interstellar", 2014, "Sci-Fi", 8.6, "Space exploration");

        UserMovie userMovie = new UserMovie(movie, 9.0, UserMovie.Status.Watched);
        MovieList movieList = user.getMovieLists().get("Sci-Fi Movies");
        movieList.addMovie(userMovie);

        assertEquals(1, movieList.getMovies().size());
        assertEquals("Interstellar", movieList.getMovies().get(0).getMovie().getMovieTitle());
    }
}

