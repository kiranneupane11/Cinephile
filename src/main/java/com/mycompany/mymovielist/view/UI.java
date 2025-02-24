//package com.mycompany.mymovielist.view;
//
//import com.mycompany.mymovielist.controller.*;
//import com.mycompany.mymovielist.model.*;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import java.util.*;
//
//public class UI {
//    private final Scanner scanner;
//    private final EntityManagerFactory emf;
//    private final EntityManager entityManager;
//    private final UIHandler uiHandler;
//    private User loggedInUser;
//
//    public UI() {
//        this.scanner = new Scanner(System.in);
//        this.emf = Persistence.createEntityManagerFactory("MovieListPU");
//        this.entityManager = emf.createEntityManager();
//        this.uiHandler = new UIHandler(entityManager);
//    }
//
//    public void start() {
//        System.out.println("Welcome to CinePhile");
//        System.out.println("1. Login");
//        System.out.println("2. Sign Up");
//        System.out.print("Choose an option: ");
//
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1 -> login();
//            case 2 -> signup();
//            default -> System.out.println("Invalid choice. Exiting...");
//        }
//
//        if (loggedInUser != null) {
//            showMenu();
//        }
//    }
//
//    private void login() {
//        System.out.print("Enter your username: ");
//        String username = scanner.nextLine();
//        System.out.print("Enter your password: ");
//        String password = scanner.nextLine();
//
//        Optional<User> userOpt = uiHandler.login(username, password);
//        if (userOpt.isEmpty()) {
//            System.out.println("Invalid username or password.");
//            return;
//        }
//
//        loggedInUser = userOpt.get();
//    }
//
//    private void signup() {
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();
//        System.out.print("Enter email: ");
//        String email = scanner.nextLine();
//        System.out.print("Enter password: ");
//        String password = scanner.nextLine();
//
//        if (uiHandler.signup(username, email, password)) {
//            System.out.println("Signup successful! Welcome, " + username + "!");
//        } else {
//            System.out.println("Error: Username or email already exists.");
//        }
//    }
//
//    private void showMenu() {
//        while (true) {
//            System.out.println("\nWhat would you like to do?");
//            System.out.println("1. View Available Movies");
//            System.out.println("2. Add to Watch List");
//            System.out.println("3. View Your Lists");
//            System.out.println("4. Log Out");
//            System.out.print("Choose an option: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1 -> viewAvailableMovies();
//                case 2 -> addMovieToList();
//                case 3 -> viewMovieLists();
//                case 4 -> {
//                    System.out.println("Logging out...");
//                    loggedInUser = null;
//                    start();
//                    return;
//                }
//                default -> System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }
//
//    private void viewAvailableMovies() {
//        List<Movie> movies = uiHandler.getAvailableMovies();
//        if (movies.isEmpty()) {
//            System.out.println("No movies available.");
//        } else {
//            for (Movie movie : movies) {
//                System.out.println(movie.getMovieID() + ". " + movie.getMovieTitle());
//            }
//        }
//    }
//
//    private void addMovieToList() {
//        viewAvailableMovies();
//        System.out.print("Enter movie ID: ");
//        long movieId = scanner.nextLong();
//        System.out.print("Enter your rating: ");
//        double rating = scanner.nextDouble();
//        scanner.nextLine();
//
//        System.out.println("Select status:");
//        for (UserMovie.Status status : UserMovie.Status.values()) {
//            System.out.println(status.ordinal() + 1 + ". " + status);
//        }
//        System.out.print("Enter choice: ");
//        int statusChoice = scanner.nextInt();
//        scanner.nextLine();
//        
//        UserMovie.Status status = UserMovie.Status.values()[statusChoice - 1];
//
//        if (uiHandler.addMovieToList(loggedInUser, movieId, rating, status)) {
//            System.out.println("Movie added successfully!");
//        } else {
//            System.out.println("Invalid movie ID.");
//        }
//    }
//
//    private void viewMovieLists() {
//        List<MovieList> lists = uiHandler.getUserLists(loggedInUser);
//        if (lists.isEmpty()) {
//            System.out.println("No movie lists found.");
//            return;
//        }
//
//        for (MovieList list : lists) {
//            System.out.println(list.getListID() + ". " + list.getListName());
//        }
//        displayList();
//    }
//    
//    private void displayList(){
//        System.out.println("Enter List ID to view it: ");
//        long listID = scanner.nextLong();
//        
//        List<UserMovie> movies = uiHandler.getMoviesInList(listID);
//        
//        for(UserMovie userMovie: movies ){
//            Movie movie = userMovie.getMovie();
//            System.out.println("ID: " + movie.getMovieID() + 
//                               " | Title: " + movie.getMovieTitle() + 
//                               " | Genre: " + movie.getGenre() +
//                               " | Rating: " + userMovie.getRating() + 
//                               " | Status: " + userMovie.getStatus() +
//                               " | Description: " + movie.getDescription());
//        }
//    }
//}
