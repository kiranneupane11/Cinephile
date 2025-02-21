///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.mymovielist;
//
//import com.mycompany.mymovielist.model.Movie;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//
//
///**
// *
// * @author kiran
// */
//public class MovieTest {
//    
//    @ParameterizedTest
//    @CsvSource({
//        "-1, 'Inception', 2010, 'Sci-Fi', 8.5, 'Lots of dreams.' ", // Negative movieID
//        "0, 'Inception', 2010, 'Sci-Fi', 8.5, 'Lots of dreams'",   // Invalid movieID
//    })
//    
//    void testMovieID(int movieID, String title, int releaseYear, String genre, double rating, String description){
//        assertThrows(IllegalArgumentException.class, 
//            () -> new Movie(movieID, title, releaseYear, genre, rating, description));
//    }
//    
//    @ParameterizedTest
//    @CsvSource({
//        "'', 2010, 'Sci-Fi', 8.5, 'Great movie'",  // Empty title
//        "'MovieWith   VeryLongTitle   ThatExceedsNormal   Lengthpadapdp   adpadpadp   adpadpad   papdpap   apapspapspdpsdaspdsds', 2010, 'Sci-Fi', 8.5, 'Great movie'" // Long title
//    })
//    void testMovieTitle(String title, int releaseYear, String genre, double rating, String description) {
//        assertThrows(IllegalArgumentException.class, () -> new Movie(1, title, releaseYear, genre, rating, description));
//    }
//    
//    @ParameterizedTest
//    @CsvSource({
//        "1899, 'Sci-Fi', 8.5, 'Great movie'", // Below valid range
//        "2026, 'Sci-Fi', 8.5, 'Great movie'", // Invalid Future year 
//    })
//    void testReleaseYear(int releaseYear, String genre, double rating, String description){
//        assertThrows(IllegalArgumentException.class, () -> new Movie(1, "Valid Title", releaseYear, genre, rating, description));
//    }
//    
//    @ParameterizedTest
//    @CsvSource({
//        "0, 'Good movie'",  // Below valid range
//        "10.1, 'Good movie'", // Above valid range
//        "-5, 'Bad rating'", // Negative rating 
//    })
//    void testRating(double rating, String description){
//        assertThrows(IllegalArgumentException.class, () -> new Movie(1, "Valid Title", 2000, "Valid Genre", rating, description));
//    }
//}
