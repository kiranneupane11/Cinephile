/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.repository;

import com.mycompany.mymovielist.model.*;
import jakarta.persistence.EntityManager;

/**
 *
 * @author kiran
 */
public class MovieRepository extends DatabaseRepository<Movie, Long> {
    public MovieRepository(EntityManager entityManager) {
        super(Movie.class, entityManager);
    }
    
}
