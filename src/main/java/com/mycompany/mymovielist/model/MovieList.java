/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;
import java.util.*;
import jakarta.persistence.*;


/**
 *
 * @author kiran
 */

@Entity
@Table(name = "user_playlist", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "name"}))
public class MovieList extends BaseEntity {
    
    @Column(name = "List_Name", nullable = false)
    private String listName;
    
    @Column(name = "user_id")
    private Long userId;

    public MovieList() {
    }
    
    public MovieList(String listName, Long userId) { 
        this.listName = listName;
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
    
    public List<MovieList> getMovieLists() {
    return movieLists;
    }  
    
    public MovieList createOrGetMovieList(String listName) {
    return movieLists.stream()
                     .filter(list -> list.getListName().equalsIgnoreCase(listName))
                     .findFirst()
                     .orElseGet(() -> {
                         MovieList newList = new MovieList(listName);
                         movieLists.add(newList);
                         return newList;
                     });
    }

}