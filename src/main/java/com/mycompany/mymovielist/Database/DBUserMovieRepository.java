/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.Database;
import com.mycompany.mymovielist.model.*;
import jakarta.persistence.EntityManager;

/**
 *
 * @author kiran
 */
public class DBUserMovieRepository extends DatabaseRepository<UserMovie, Integer> {
    public DBUserMovieRepository(EntityManager entityManager) {
        super(UserMovie.class, entityManager);
    }
    
}
