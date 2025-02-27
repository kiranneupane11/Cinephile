/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.Database;

/**
 *
 * @author kiran
 */
import com.mycompany.mymovielist.model.*;
import jakarta.persistence.EntityManager;
import java.util.*;


public class DBMovieListRepository extends DatabaseRepository<MovieList, Long> {
    public DBMovieListRepository(EntityManager entityManager) {
        super(MovieList.class, entityManager);
    }
    
    public List<MovieList> getListByUserId(Long userId) {
    return entityManager.createQuery(
            "SELECT m FROM MovieList m WHERE m.userId = :userId", MovieList.class)
            .setParameter("userId", userId)
            .getResultList();
    }
}
