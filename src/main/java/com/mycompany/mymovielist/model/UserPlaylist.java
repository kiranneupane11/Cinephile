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
public class UserPlaylist extends BaseEntity {
    
    @Column(name = "name", nullable = false)
    private String listName;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public UserPlaylist() {
    }
    
    public UserPlaylist(String listName, User userId) { 
        this.listName = listName;
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
   
    public User getUserID(){
        return userId;
    }
}