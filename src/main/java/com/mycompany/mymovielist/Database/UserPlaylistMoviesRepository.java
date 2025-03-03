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
public class UserPlaylistMoviesRepository extends DatabaseRepository<UserPlaylistMovies, Long> {
    public UserPlaylistMoviesRepository(EntityManager entityManager) {
        super(UserPlaylistMovies.class, entityManager);
    }
    
    public List<Object[]> getMoviesFromPlaylist(Long playlistId, Long userId) {
    return entityManager.createQuery(
        "SELECT um, m FROM UserPlaylistMovies upm " + 
        "JOIN UserPlaylist ml ON upm.userPlaylistId = ml.id " + 
        "JOIN UserMovieRating um ON upm.movieId = um.movieId AND um.userId = :userId " + 
        "JOIN Movie m ON um.movieId = m.id " +
        "WHERE ml.id = :playlistId AND ml.userId = :userId", Object[].class)
        .setParameter("playlistId", playlistId)
        .setParameter("userId", userId)
        .getResultList();
}
} 
