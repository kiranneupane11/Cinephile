/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.view;

import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.service.*;
import com.mycompany.mymovielist.repository.*;
import java.util.*;


/**
 *
 * @author kiran
 */
public class UI {
    private final Scanner scanner;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final UserListManager userListManager;

    public UI() {
        this.scanner = new Scanner(System.in);
        this.userRepository = new UserRepository();
        this.movieRepository = new MovieRepository();
        this.userListManager = new UserListManager(userRepository);
        preloadMovies();
    }

    private void preloadMovies() {
        movieRepository.add(1, new Movie(1, "Inception", 2010, "Sci-Fi", 8.8, "A mind-bending thriller"));
        movieRepository.add(2, new Movie(2, "The Dark Knight", 2008, "Action", 9.0, "Batman fights Joker"));
        movieRepository.add(3, new Movie(3, "Interstellar", 2014, "Sci-Fi", 8.6, "Space exploration"));
        movieRepository.add(4, new Movie(4, "The Matrix", 1999, "Sci-Fi", 8.7, "A hacker discovers reality is simulated"));
        movieRepository.add(5, new Movie(5, "Fight Club", 1999, "Drama", 8.8, "A man starts an underground fight club"));
    }

    public void start() {
        System.out.println("Welcome to Movie List App!");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        User user = userRepository.getAll()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseGet(() -> {
                    User newUser = new User(new Random().nextInt(1000), username);
                    userRepository.add(newUser.getUserID(), newUser);
                    return newUser;
                });

        System.out.println("Hello, " + user.getUsername() + "!");

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. View Available Movies");
            System.out.println("2. Create Movie List");
            System.out.println("3. Add Movie to List");
            System.out.println("4. View My Movie Lists");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableMovies();
                    break;
                case 2:
                    createMovieList(user);
                    break;
                case 3:
                    addMovieToList(user);
                    break;
                case 4:
                    viewMovieLists(user);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void viewAvailableMovies() {
        System.out.println("\nAvailable Movies:");
        for (Movie movie : movieRepository.getAll()) {
            System.out.println(movie.getMovieID() + ". " + movie.getMovieTitle() + " (" + movie.getReleaseYear() + ") - " + movie.getGenre());
        }
    }

    private void createMovieList(User user) {
        System.out.print("Enter the name of the new movie list: ");
        String listName = scanner.nextLine();
        if (user.createMovieList(listName)) {
            System.out.println("Movie list '" + listName + "' created successfully!");
        } else {
            System.out.println("A list with this name already exists.");
        }
    }

    private void addMovieToList(User user) {
        if (user.getMovieLists().isEmpty()) {
            System.out.println("You don't have any movie lists. Create one first.");
            return;
        }

        System.out.println("Select a movie to add:");
        viewAvailableMovies();
        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Movie movie = movieRepository.get(movieId).orElse(null);
        if (movie == null) {
            System.out.println("Invalid movie ID.");
            return;
        }

        System.out.println("Select a movie list:");
        user.getMovieLists().keySet().forEach(System.out::println);
        System.out.print("Enter list name: ");
        String listName = scanner.nextLine();

        MovieList movieList = user.getMovieLists().get(listName);
        if (movieList == null) {
            System.out.println("List not found.");
            return;
        }
        
        System.out.println("Select movie status:");
        for (UserMovie.Status status : UserMovie.Status.values()) {
            System.out.println(status.ordinal() + 1 + ". " + status);
        }
        System.out.print("Enter choice (1-" + UserMovie.Status.values().length + "): ");

        int statusChoice = scanner.nextInt();
        scanner.nextLine(); 

        if (statusChoice < 1 || statusChoice > UserMovie.Status.values().length) {
            System.out.println("Invalid choice. Defaulting to 'Plan To Watch'.");
            statusChoice = 2; // Default to 'To Watch'
        }

        UserMovie.Status status = UserMovie.Status.values()[statusChoice - 1];
        

        System.out.print("Enter your rating for this movie: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); 
     

        UserMovie userMovie = new UserMovie(movie, rating, status);
        movieList.addMovie(userMovie);
        System.out.println("Movie added to list successfully!");
    }

    private void viewMovieLists(User user) {
        if (user.getMovieLists().isEmpty()) {
            System.out.println("You don't have any movie lists.");
            return;
        }

        System.out.println("\nYour Movie Lists:");
        for (Map.Entry<String, MovieList> entry : user.getMovieLists().entrySet()) {
            System.out.println("List: " + entry.getKey());
            for (UserMovie um : entry.getValue().getMovies()) {
                System.out.println(" - " + um.getMovie().getMovieTitle() + " (" + um.getRating() + "/10)");
            }
        }
    }

}

