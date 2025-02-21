package com.mycompany.mymovielist.view;

import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.Database.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.*;

public class DBUI {
    private final Scanner scanner;
    private final EntityManagerFactory emf;
    private final EntityManager entityManager;
    private final DBUserRepository userRepository;
    private final DBMovieRepository movieRepository;
    private final DBUserMovieRepository userMovieRepository;
    private final DBMovieListRepository movieListRepository;
    private User loggedInUser;
    private boolean isAdmin;

    public DBUI() {
        this.scanner = new Scanner(System.in);
        this.emf = Persistence.createEntityManagerFactory("MovieListPU");
        this.entityManager = emf.createEntityManager();
        this.userRepository = new DBUserRepository(entityManager);
        this.movieRepository = new DBMovieRepository(entityManager);
        this.userMovieRepository = new DBUserMovieRepository(entityManager);
        this.movieListRepository = new DBMovieListRepository(entityManager);
        this.loggedInUser = null;
        this.isAdmin = false;
    }

    public void start() {
        System.out.println("Welcome to CinePhile");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> login();
            case 2 -> signup();
            default -> {
                System.out.println("Invalid choice. Exiting...");
                return;
            }
        }
        
        showMenu();

    }
    
    private void showMenu() {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View Available Movies");
            System.out.println("2. Add to Watch List");
            System.out.println("3. Create Custom List");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewAvailableMovies();
                case 2 -> {
                    viewAvailableMovies();
                    addMovieToList(loggedInUser);
                }
                case 3 -> createCustomList(loggedInUser);
                case 4 -> viewMovieLists(loggedInUser);
                case 5 -> {
                    System.out.println("Logging out...");
                    start();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private void login() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty() || !userOpt.get().getPassword().equals(password)) {
            System.out.println("Invalid username or password.");
            return;
        }

        loggedInUser = userOpt.get();
        isAdmin = username.equalsIgnoreCase("admin");
        System.out.println("Welcome, " + loggedInUser.getUsername() + "!");
    }
    
    private void signup(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (userRepository.findByUsername(username).isPresent()) {
            System.out.print("Error: Username already exists.");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            System.out.print("Error: Email already exists.");
        }
        
        User newUser = new User(username, email, password);
        userRepository.add(newUser.getUserID(), newUser);
        System.out.println("Hello, " + newUser.getUsername() + "!");
    }

    private void viewAvailableMovies() {
        System.out.println("\nAvailable Movies:");
        List<Movie> movies = movieRepository.getAll();
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
        } else {
            for (Movie movie : movies) {
                System.out.println(movie.getMovieID() + ". " + movie.getMovieTitle() + " (" + movie.getReleaseYear() + ") - " + movie.getGenre());
            }
        }
    }

    private void createCustomList(User user) {
        System.out.print("Enter name of custom list: ");
        String listName = scanner.nextLine();

        MovieList customList = user.createOrGetMovieList(listName);
        movieListRepository.add(customList.getListID(), customList);
        
        System.out.println("Choose a movie to add:");
        viewAvailableMovies();
        
        System.out.print("Enter movie ID: ");
        long movieId = scanner.nextLong();
        scanner.nextLine();
        
        Movie movie = movieRepository.get(movieId).orElse(null);
        if (movie == null) {
            System.out.println("Invalid movie ID.");
            return;
        }

        System.out.print("Enter your rating for this movie: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("Select movie status:");
        for (UserMovie.Status status : UserMovie.Status.values()) {
            System.out.println(status.ordinal() + 1 + ". " + status);
        }
        System.out.print("Enter choice (1-" + UserMovie.Status.values().length + "): ");

        int statusChoice = scanner.nextInt();
        scanner.nextLine();
        UserMovie.Status status = statusChoice < 1 || statusChoice > UserMovie.Status.values().length ? UserMovie.Status.Plan_To_Watch : UserMovie.Status.values()[statusChoice - 1];
        UserMovie userMovie = new UserMovie(movie, customList, rating, status);
        customList.addMovie(userMovie); 
        userMovieRepository.add(userMovie.getId(), userMovie);
              
        System.out.println("Movie list '" + listName + "' created successfully!");
    }

    private void addMovieToList(User user) {
        
        System.out.println("Choose a movie to add:");
        viewAvailableMovies();
        System.out.print("Enter movie ID: ");
        long movieId = scanner.nextLong();
        scanner.nextLine();

        Movie movie = movieRepository.get(movieId).orElse(null);
        if (movie == null) {
            System.out.println("Invalid movie ID.");
            return;
        }

        System.out.print("Enter your rating for this movie: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("Select movie status:");
        for (UserMovie.Status status : UserMovie.Status.values()) {
            System.out.println(status.ordinal() + 1 + ". " + status);
        }
        System.out.print("Enter choice (1-" + UserMovie.Status.values().length + "): ");

        int statusChoice = scanner.nextInt();
        scanner.nextLine();
        UserMovie.Status status = statusChoice < 1 || statusChoice > UserMovie.Status.values().length ? UserMovie.Status.Plan_To_Watch : UserMovie.Status.values()[statusChoice - 1];

        String listName = status.name();
        MovieList createdList = user.createOrGetMovieList(listName);
        movieListRepository.add(createdList.getListID(), createdList);

        UserMovie userMovie = new UserMovie(movie, createdList, rating, status);
        createdList.addMovie(userMovie); 
        userMovieRepository.add(userMovie.getId(), userMovie);
        
        System.out.println("Movie added to list successfully!");
    }

    private void viewMovieLists(User user) {
        System.out.println("Your Lists: ");
        List<MovieList> userLists = movieListRepository.findByUser(user);
        
        if (userLists.isEmpty()) {
        System.out.println("You have no movie lists.");
        return;
        }
        
        for(MovieList list : userLists ){
            System.out.println("ID: " + list.getListID() + " | Name: " + list.getListName());
        }
       
        System.out.println("Enter List ID:  ");
        long listID = scanner.nextLong();
        Optional<MovieList> selectedList = movieListRepository.get(listID);        

        if (selectedList.isEmpty()) {
        System.out.println("Invalid List ID.");
        return;
        }
        
        MovieList movieList = selectedList.get();
        List<UserMovie> movies = userMovieRepository.findByMovieList(movieList);

        System.out.println("Movies in '" + movieList.getListName() + "':");
        
        if (movies.isEmpty()) {
            System.out.println("No movies found in this list.");
        } else {
            for(UserMovie userMovie : movies){
                Movie movie = userMovie.getMovie();
                System.out.println("ID: " + movie.getMovieID() + " | Title: " + movie.getMovieTitle() + 
                               " | Rating: " + userMovie.getRating() + 
                               " | Status: " + userMovie.getStatus());
            }
        }
        
    }

//    private void addMovieToDatabase(Admin admin) {
//        System.out.print("Enter movie ID: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//
//        System.out.print("Enter movie title: ");
//        String title = scanner.nextLine();
//
//        System.out.print("Enter release year: ");
//        int year = scanner.nextInt();
//        scanner.nextLine();
//
//        System.out.print("Enter genre: ");
//        String genre = scanner.nextLine();
//
//        System.out.print("Enter rating: ");
//        double rating = scanner.nextDouble();
//        scanner.nextLine();
//
//        System.out.print("Enter description: ");
//        String description = scanner.nextLine();
//
//        admin.addMovie(id, title, year, genre, rating, description);
//    }
//
//    private void removeMovieFromDatabase(Admin admin) {
//        System.out.print("Enter movie ID to remove: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//
//        entityManager.getTransaction().begin();
//        admin.removeMovie(id);
//        entityManager.getTransaction().commit();
//    }
}
