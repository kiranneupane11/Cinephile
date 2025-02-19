package com.mycompany.mymovielist.view;

import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.service.*;
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
    private final UserListManager userListManager;

    public DBUI() {
        this.scanner = new Scanner(System.in);
        this.emf = Persistence.createEntityManagerFactory("MovieListPU");
        this.entityManager = emf.createEntityManager();
        this.userRepository = new DBUserRepository(entityManager);
        this.movieRepository = new DBMovieRepository(entityManager);
        this.userMovieRepository = new DBUserMovieRepository(entityManager);
        this.userListManager = new UserListManager(userRepository);
        this.movieListRepository = new DBMovieListRepository(entityManager);
    }

    public void start() {
        System.out.println("Welcome to Movie List App!");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        User user = userRepository.findByUsername(username).orElseGet(() -> {
            User newUser = new User(new Random().nextInt(1000), username);
            userRepository.add(newUser.getUserID(), newUser);
            return newUser;
        });

        System.out.println("Hello, " + user.getUsername() + "!");
        boolean isAdmin = username.equalsIgnoreCase("admin"); // Simple check for admin access
        Admin admin = isAdmin ? new Admin(user.getUserID(), username, movieRepository) : null;

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. View Available Movies");
            System.out.println("2. Create Movie List");
            System.out.println("3. Add Movie to List");
            System.out.println("4. View My Movie Lists");
            if (isAdmin) {
                System.out.println("5. Add Movie to Database");
                System.out.println("6. Remove Movie from Database");
            }
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewAvailableMovies();
                case 2 -> createMovieList(user);
                case 3 -> addMovieToList(user);
                case 4 -> viewMovieLists(user);
                case 5 -> {
                    if (isAdmin) addMovieToDatabase(admin);
                    else System.out.println("Invalid option.");
                }
                case 6 -> {
                    if (isAdmin) removeMovieFromDatabase(admin);
                    else System.out.println("Invalid option.");
                }
                case 0 -> {
                    running = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
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

    private void createMovieList(User user) {
        System.out.print("Enter the name of the new movie list: ");
        String listName = scanner.nextLine();

        if (user.createMovieList(listName)) {
            MovieList newList = user.getMovieLists().get(listName);

            // ✅ Persist the new list in the database
            movieListRepository.add(newList.getListID(), newList);

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
        scanner.nextLine();

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
        UserMovie.Status status = statusChoice < 1 || statusChoice > UserMovie.Status.values().length ? UserMovie.Status.Plan_To_Watch : UserMovie.Status.values()[statusChoice - 1];

        System.out.print("Enter your rating for this movie: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();

        UserMovie userMovie = new UserMovie(movie, movieList, rating, status);
        userMovieRepository.add(userMovie.getId(), userMovie);
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

    private void addMovieToDatabase(Admin admin) {
        System.out.print("Enter movie ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();

        System.out.print("Enter release year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        admin.addMovie(id, title, year, genre, rating, description);
    }

    private void removeMovieFromDatabase(Admin admin) {
        System.out.print("Enter movie ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        entityManager.getTransaction().begin();
        admin.removeMovie(id);
        entityManager.getTransaction().commit();
    }
}
