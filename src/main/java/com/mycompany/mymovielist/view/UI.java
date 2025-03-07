/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.view;
import com.mycompany.mymovielist.commands.ViewAvailableMoviesCommand;
import com.mycompany.mymovielist.commands.AddMovieToListCommand;
import com.mycompany.mymovielist.commands.LogoutCommand;
import com.mycompany.mymovielist.commands.ViewMovieListsCommand;
import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.service.*;
import com.mycompany.mymovielist.util.*;
import com.mycompany.mymovielist.repository.*;
import jakarta.persistence.*;




/**
 *
 * @author kiran
 */
public class UI {
    
    private final ConsoleIO io;
    
    private final AuthenticationService authenticationService;
    private final MovieService movieService;
    private final PlaylistService playlistService;

    private User loggedInUser;
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public UI() {
        this.io = new ConsoleIO();
        this.emf = Persistence.createEntityManagerFactory("MovieListPU");
        this.em = emf.createEntityManager();
        
        UserRepository userRepository = new UserRepository(em);
        MovieRepository movieRepository = new MovieRepository(em);
        UserPlaylistRepository userPlaylistRepository = new UserPlaylistRepository(em);
        UserPlaylistMoviesRepository userPlaylistMoviesRepository = new UserPlaylistMoviesRepository(em);
        UserMovieRatingRepository userMovieRatingRepository = new UserMovieRatingRepository(em);

        PasswordService passwordService = new BasicPasswordService();

        this.authenticationService = new AuthenticationService(userRepository, passwordService);
        this.movieService = new MovieService(movieRepository);
        this.playlistService = new PlaylistService(
                userPlaylistRepository,
                userPlaylistMoviesRepository,
                userMovieRatingRepository,
                movieRepository
        );
    }

    public void start() {
        io.displayMessage("Welcome to CinePhile");
        io.displayMessage("1. Login");
        io.displayMessage("2. Sign Up");
        
        int choice = io.readInt("Choose an Option: ");
        switch (choice) {
            case 1 -> login();
            case 2 -> signup();
            default -> {
                io.displayMessage("Invalid Choice. Exiting...");
                return;
            }
        }

        if (loggedInUser != null) {
            // Set up the navigation manager and register commands
            NavigationManager navManager = new NavigationManager(io);

            // Pass the required services (and the logged-in user) to each command
            navManager.registerCommand(1, new ViewAvailableMoviesCommand(io, movieService));
            navManager.registerCommand(2, new AddMovieToListCommand(io,movieService, playlistService, loggedInUser));
            navManager.registerCommand(3, new ViewMovieListsCommand(io, playlistService, loggedInUser));
            navManager.registerCommand(4, new LogoutCommand(this, navManager));

            // Start navigation loop
            navManager.startNavigation();
        }
    }

    private void login() {
        String usernameOrEmail = io.readString("Enter Username or E-mail: ");
        String password = io.readPassword("Enter Password: ");

        authenticationService.login(usernameOrEmail, password)
            .ifPresentOrElse(
                user -> {
                    loggedInUser = user;
                    io.displayMessage("Welcome, " + user.getUsername() + "!");
                },
                () -> io.displayMessage("Invalid Username/E-mail or Password! Try Again.")
            );
    }

    private void signup() {
        String username = io.readString("Enter your username: ");
        String email = io.readString("Enter your email address: ");
        String password = io.readString("Enter your password: ");

        User newUser = new User(username, email, password);

        authenticationService.signup(newUser)
            .ifPresentOrElse(
                user -> io.displayMessage("Sign up successful! Welcome, " + user.getUsername() + "!"),
                () -> io.displayMessage("Error: Username or email already exists.")
            );
        
        start();
    }

    public void logout() {
        loggedInUser = null;
        io.displayMessage("Logging out...");
        start();
    }
}
