/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.model;

import java.util.*;
import jakarta.persistence.*;
import com.mycompany.mymovielist.util.PasswordUtil;


/**
 *
 * @author kiran
 */

@Entity
@Table(name = "user")
public class User extends BaseEntity {
    
    @Column(unique = true, nullable = false)
    protected String username;
    
    @Column(unique = true, nullable = false)
    protected String email;
    
    @Column(nullable = false)
    protected String password;

    public User() {} 
    
    public User(String username, String email, String rawPassword) {
        this.username = username;
        this.email = email;
        this.password = PasswordUtil.hashPassword(rawPassword);
    }

    public String getUsername() {
        return username;
    }
    
    public boolean checkPassword(String rawPassword) {
        return PasswordUtil.verifyPassword(rawPassword, this.password);
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
