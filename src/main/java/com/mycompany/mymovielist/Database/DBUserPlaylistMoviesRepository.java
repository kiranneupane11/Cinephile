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
    
    public List<UserPlaylistMovies> findByPlaylistId(Long playlistId) {
        return entityManager.createQuery(
            "SELECT mli FROM MovieListItem mli WHERE mli.userPlaylist.id = :playlistId", 
            UserPlaylistMovies.class)
            .setParameter("playlistId", playlistId)
            .getResultList();
    }
} 
