package com.mycompany.mymovielist.view;

import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.repository.GenericRepository;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GenericRepository<User> userRepository = new GenericRepository<>();
    private static final GenericRepository<Movie> movieRepository = new GenericRepository<>();
    
    public static void main(String[] args) {
        Admin admin = new Admin("Admin");
        userRepository.add(admin);
        seedMovieRepository();
        
        while (true) {
            System.out.println("\nWelcome to My Movie List");
            System.out.println("1. Admin Panel");
            System.out.println("2. User Panel");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> adminPanel(admin);
                case 2 -> userPanel();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    private static void seedMovieRepository() {
        movieRepository.add(new Movie(1, "Inception", 2010, "Sci-Fi",8.8, "A mind-bending thriller"));
        movieRepository.add(new Movie(2, "The Dark Knight", 2008, "Action",9.3, "Batman vs Joker"));
        movieRepository.add(new Movie(3, "Interstellar", 2014, "Sci-Fi",7.7, "Exploring the cosmos"));
    }
    
    private static void adminPanel(Admin admin) {
        while (true) {
            System.out.println("\nAdmin Panel");
            System.out.println("1. Add Movie");
            System.out.println("2. View All Movies");
            System.out.println("3. View All Users");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter release year: ");
                    int releaseYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter movie rating (1-10): ");
                    double rating = scanner.nextDouble();
                    scanner.nextLine();
                    Movie movie = new Movie(movieRepository.getAll().size() + 1, title, releaseYear,genre, rating, description);
                    admin.addMedia(movieRepository, movie);
                }
                case 2 -> {
                    System.out.println("\nMovies in Repository:");
                    for (Movie movie : movieRepository.getAll()) {
                        System.out.println(movie.getMovieID() + ". " + movie.getMovieTitle() +
                        " (" + movie.getReleaseYear() + ") - " + movie.getGenre() + "| Rating:" + movie.getRating() +
                        " | Description: "+ movie.getDescription());
                    }
                }
                case 3 -> {
                    admin.viewAllUsers(userRepository);
                }
                case 4 -> { return; }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    private static void userPanel() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        User user = new User(username);
        userRepository.add(user);
        
        while (true) {
            System.out.println("\nUser Menu - " + user.getUsername());
            System.out.println("1. Create Movie List");
            System.out.println("2. View My Movie Lists");
            System.out.println("3. Add Movie to List");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter list name: ");
                    String listName = scanner.nextLine();
                    user.createList(listName);
                    userRepository.add(user);
                }
                case 2 -> {
                    user.viewAllLists();
                    System.out.print("Enter list name: ");
                    String listName = scanner.nextLine();
                    user.viewThisList(listName);
                }
                case 3 -> {
                    System.out.print("Enter list name: ");
                    String listName = scanner.nextLine();
                    System.out.println("Available Movies:");
                    for (Movie movie : movieRepository.getAll()) {
                        System.out.println("- " + movie.getMovieTitle());
                    }
                    System.out.print("Enter movie title to add: ");
                    String movieTitle = scanner.nextLine();
                    for (Movie movie : movieRepository.getAll()) {
                        if (movie.getMovieTitle().equalsIgnoreCase(movieTitle)) {
                            user.addToList(listName, movie);
                            break;
                        }
                    }
                }
                case 4 -> { return; }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
