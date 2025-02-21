/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.Database;
import com.mycompany.mymovielist.model.*;
import jakarta.persistence.EntityManager;
import java.util.*;

/**
 *
 * @author kiran
 */
public class DBUserMovieRepository extends DatabaseRepository<UserMovie, Long> {
    public DBUserMovieRepository(EntityManager entityManager) {
        super(UserMovie.class, entityManager);
    }
    
    public List<UserMovie> findByMovieList(MovieList movieList){
        return entityManager.createQuery(
        "SELECT um FROM UserMovie um WHERE um.movieList = :movieList", UserMovie.class)
                .setParameter("movieList", movieList)
                .getResultList();
    }
}
