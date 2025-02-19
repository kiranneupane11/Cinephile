package com.mycompany.mymovielist.view;
import jakarta.persistence.*;
import com.mycompany.mymovielist.Database.*;
import com.mycompany.mymovielist.model.*;



public class Main {
    public static void main(String[] args) {
        new DBUI().start();
            

//    /// Initialize EntityManagerFactory and EntityManager
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieListPU");
//        EntityManager em = emf.createEntityManager();
//
//        // Create repository and pass the EntityManager to it
//        DBUserRepository userRepository = new DBUserRepository(em);
//
//        
//        User user = new User(2000, "Mary Jane");
//        userRepository.add(2000, user); 
//
//        // Retrieve and print the user by username
//        userRepository.findByUsername("Mary Jane")
//                .ifPresent(u -> System.out.println("User found: ID = " + u.getUserID() + ", Username = " + u.getUsername()));
//
//        // Close EntityManager and EntityManagerFactory
//        em.close();
//        emf.close();
//
    }
}

