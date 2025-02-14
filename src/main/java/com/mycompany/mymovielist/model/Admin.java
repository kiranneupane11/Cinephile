/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;

import com.mycompany.mymovielist.repository.GenericRepository;
/**
 *
 * @author kiran
 */
public class Admin extends User{
      public Admin(String username) {
        super(username);
    }
      public <T> void addMedia(GenericRepository<T> repository, T item){
          repository.add(item);
          System.out.println("Admin added: " + ((Movie) item).getMovieTitle());
          
    }
      public <T> void removeMedia(GenericRepository<T> repository, T item) {
        repository.remove(item);
        System.out.println("Admin removed: " + ((Movie) item).getMovieTitle());
    }

    public <T> void updateMedia(GenericRepository<T> repository, T oldItem, T newItem) {
        repository.remove(oldItem);
        repository.add(newItem);
        System.out.println("Admin updated: " + ((Movie) newItem).getMovieTitle());
    }
    
    public void addUser(GenericRepository<User> userRepo, User user) {
        userRepo.add(user);
        System.out.println("Admin added user: " + user.getUsername() + " (UserID: " + user.getUserID() + ")");
    }

    public void removeUser(GenericRepository<User> userRepo, User user) {
        userRepo.remove(user);
        System.out.println("Admin removed user: " + user.getUsername() + " (UserID: " + user.getUserID() + ")");
    }

    // Admin can view all users
    public void viewAllUsers(GenericRepository<User> userRepo) {
        System.out.println("\nRegistered Users:");
        for (User user : userRepo.getAll()) {
            System.out.println("- " + user.getUsername());
            
        }
    }
}
