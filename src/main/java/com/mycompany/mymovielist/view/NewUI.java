/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.view;
import java.util.*;
import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.controller.*;
import jakarta.persistence.*;




/**
 *
 * @author kiran
 */
public class NewUI {
    
    private final ConsoleIO io;
    private final UIHandler uiHandler;
    private User loggedInUser;
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public NewUI(){
        this.io = new ConsoleIO();
        this.emf = Persistence.createEntityManagerFactory("MyMovieList");
        this.em = emf.createEntityManager();
        this.uiHandler = new UIHandler(em);
    }
    
    public void start(){
        io.displayMessage("Welcome to CinePhile");
        io.displayMessage("1. Login");
        io.displayMessage("2. Sign Up");
        int choice = io.readInt("Choose an Option: ");
        switch(choice){
            case 1 -> login();
            case 2 -> signup();
            default -> {
                io.displayMessage("Invalid Choice. Exiting...");
                return;
            }
        
        }
    }
    
    private void login(){
        String username = io.readString("Enter your username");
        String password = io.readString("Enter password");
        
        uiHandler.login(username, password)
         .ifPresentOrElse(
             user -> {
                 loggedInUser = user;
             },
             () -> io.displayMessage("Invalid Username or Password")
         );
    }
    
    private void signup(){
        String username = io.readString("Enter your username");
        String email = io.readString("Enter Email address");
        String password = io.readString("Enter password");
        
        uiHandler.signup(username, email, password)
                .ifPresentOrElse(
                        user -> io.displayMessage("Sign up successful! Welcome, " + username + "!"),
                        () -> io.displayMessage("Error: Username or email already exists.")
                ); 
    }
    
    public void logout(){
        loggedInUser = null;
        io.displayMessage("Logging out...");
        start();
    }
}
