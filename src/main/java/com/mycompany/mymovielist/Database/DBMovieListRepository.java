/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.Database;

/**
 *
 * @author kiran
 */
import com.mycompany.mymovielist.model.MovieList;
import jakarta.persistence.EntityManager;

public class DBMovieListRepository extends DatabaseRepository<MovieList, Long> {
    public DBMovieListRepository(EntityManager entityManager) {
        super(MovieList.class, entityManager);
    }
}
