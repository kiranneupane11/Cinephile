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
    
    public List<MovieList> getListsByUserId(User user){
        return entityManager.createQuery("SELECT ml FROM MovieList ml WHERE ml.userId = :userId", MovieList.class)
            .setParameter("userId", user.getId())
            .getResultList();
    }
    
    public Optional<MovieList> findByUserIdAndListName(MovieList movieList) {
    return entityManager.createQuery(
        "SELECT ml FROM MovieList ml WHERE ml.userId = :userId AND ml.listName = :listName",
        MovieList.class)
        .setParameter("userId", movieList.getUserID())
        .setParameter("listName", movieList.getListName())
        .getResultStream()
        .findFirst();
    }
}
