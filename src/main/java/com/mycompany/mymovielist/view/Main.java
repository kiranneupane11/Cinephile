package com.mycompany.mymovielist.view;

import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.service.*;
import com.mycompany.mymovielist.repository.*;

public class Main {
    public static void main(String[] args) {
        // Create repositories
        UserRepository userRepository = new UserRepository();
        UserListManager userListManager = new UserListManager(userRepository);
        MovieRepository movieRepository = new MovieRepository();
        UserMovieRepository userMovieRepository = new UserMovieRepository();

        // Create sample movie
        Movie movie = new Movie(1, "Inception", 2010, "Sci-Fi", 8.8, "A mind-bending thriller");
        movieRepository.add(1, movie);
        

        // Create user
        User user = new User(1001, "JohnDoe");
        userRepository.add(1001, user);

        // Create movie list for user
        if (user.createMovieList("Favorites")) {
            System.out.println("Movie list 'Favorites' created successfully.");
        } else {
            System.out.println("Error occured");
        }

        // Create UserMovie entry
        UserMovie userMovie = new UserMovie(movie, 9.0, UserMovie.Status.Watched);
        userMovieRepository.add(1, userMovie);

        // Add movie to user's list
        userListManager.addMovieToList(1001, "Favorites", userMovie);

        // Retrieve and print sorted movie list
        System.out.println("Sorted by title: " + userListManager.sortMoviesByTitle(1001, "Favorites"));

        // Retrieve and print filtered movie list by rating
        System.out.println("Filtered by rating >= 8.5: " + userListManager.filterByRating(1001, "Favorites", 8.0));
    }
}

