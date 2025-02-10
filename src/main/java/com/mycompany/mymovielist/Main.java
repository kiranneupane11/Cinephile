package com.mycompany.mymovielist;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, MovieList> movieLists = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Movie List Manager ===");
            System.out.println("1. Add Movie");
            System.out.println("2. Remove Movie by ID");
            System.out.println("3. View Movies in a List");
            System.out.println("4. Sort Movies");
            System.out.println("5. Filter Movies");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addMovie();
                case 2 -> removeMovie();
                case 3 -> viewMovies();
                case 4 -> sortMovies();
                case 5 -> filterMovies();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addMovie() {
        System.out.print("Enter List Name: ");
        String listName = scanner.nextLine();

        // Fetch or create the list
        MovieList movieList = movieLists.computeIfAbsent(listName, MovieList::new);

        System.out.print("Enter Movie ID: ");
        int movieID = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Movie Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Release Year: ");
        int releaseYear = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter Rating (1-10): ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        try {
            Movie movie = new Movie(movieID, title, releaseYear, genre, rating, description);
            movieList.addMovie(movie);
            System.out.println("Movie added successfully to list: " + listName);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeMovie() {
        System.out.print("Enter List Name: ");
        String listName = scanner.nextLine();

        if (!movieLists.containsKey(listName)) {
            System.out.println("List does not exist.");
            return;
        }

        System.out.print("Enter Movie ID to remove: ");
        int movieID = scanner.nextInt();
        scanner.nextLine(); 

        movieLists.get(listName).removeMovieById(movieID);
        System.out.println("Movie removed if it existed in list: " + listName);
    }

    private static void viewMovies() {
        System.out.print("Enter List Name: ");
        String listName = scanner.nextLine();

        if (!movieLists.containsKey(listName)) {
            System.out.println("List does not exist.");
            return;
        }

        List<Movie> movies = movieLists.get(listName).getMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies in the list.");
        } else {
            for (Movie movie : movies) {
                System.out.println(movie.getMovieID() + ". " + movie.getMovieTitle() +
                        " (" + movie.getReleaseYear() + ") - " + movie.getGenre() +
                        " | Rating: " + movie.getRating() + " | Description: "+ movie.getDescription());
            }
        }
    }

    private static void sortMovies() {
        System.out.print("Enter List Name: ");
        String listName = scanner.nextLine();

        if (!movieLists.containsKey(listName)) {
            System.out.println("List does not exist.");
            return;
        }

        System.out.println("Sort by: 1. Title  2. Release Year  3. Rating");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Movie> sortedMovies = switch (choice) {
            case 1 -> movieLists.get(listName).sortByTitle();
            case 2 -> movieLists.get(listName).sortByReleaseYear();
            case 3 -> movieLists.get(listName).sortByRatingDescending();
            default -> {
                System.out.println("Invalid choice. Returning to menu.");
                yield null;
            }
        };

        if (sortedMovies != null) {
            System.out.println("Sorted Movies:");
            for (Movie movie : sortedMovies) {
                System.out.println(movie.getMovieTitle() + " (" + movie.getReleaseYear() + ") - Rating: " + movie.getRating());
            }
        }
    }

    private static void filterMovies() {
        System.out.print("Enter List Name: ");
        String listName = scanner.nextLine();

        if (!movieLists.containsKey(listName)) {
            System.out.println("List does not exist.");
            return;
        }

        System.out.println("Filter by: 1. Genre  2. Minimum Rating  3. Release Year Range");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Movie> filteredMovies = null;

        switch (choice) {
            case 1 -> {
                System.out.print("Enter Genre: ");
                String genre = scanner.nextLine();
                filteredMovies = movieLists.get(listName).filterByGenre(genre);
            }
            case 2 -> {
                System.out.print("Enter Minimum Rating: ");
                double minRating = scanner.nextDouble();
                filteredMovies = movieLists.get(listName).filterByMinimumRating(minRating);
            }
            case 3 -> {
                System.out.print("Enter Start Year: ");
                int startYear = scanner.nextInt();
                System.out.print("Enter End Year: ");
                int endYear = scanner.nextInt();
                filteredMovies = movieLists.get(listName).filterByReleaseYearRange(startYear, endYear);
            }
            default -> System.out.println("Invalid choice. Returning to menu.");
        }

        if (filteredMovies != null) {
            if (filteredMovies.isEmpty()) {
                System.out.println("No movies found.");
            } else {
                for (Movie movie : filteredMovies) {
                    System.out.println(movie.getMovieTitle() + " (" + movie.getReleaseYear() + ") - Rating: " + movie.getRating());
                }
            }
        }
    }
}
