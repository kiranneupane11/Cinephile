/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.repository;

import com.mycompany.mymovielist.model.*;
import java.util.*;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;

/**
 *
 * @author kiran
 */
public class UserRepository extends DatabaseRepository<User, Long> {
    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
    
    @Transactional
    public Optional<User> findByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }
    
    @Transactional
    public Optional<User> findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
}
   
