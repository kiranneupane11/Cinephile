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
public class DBUserPlaylistMoviesRepository extends DatabaseRepository<UserPlaylistMovies, Long> {
    public DBUserPlaylistMoviesRepository(EntityManager entityManager) {
        super(UserPlaylistMovies.class, entityManager);
    }
    
    public List<Object[]> getMoviesFromPlaylist(UserPlaylistMovies userPlaylistMovies) {
        return entityManager.createQuery(
            "SELECT upm, m FROM UserPlaylistMovies upm JOIN MovieList ml ON upm.userPlaylistId = ml.id"+
            "JOIN UserMovie um ON upm.movieId = um.movieId AND ml.userId = um.userId"+
            "JOIN Movie m ON um.movieId = m.id WHERE ml.id = :playlistId",
            Object[].class)
            .setParameter("playlistId", userPlaylistMovies.getUserPlaylistId())
            .getResultList();
    }
} 
