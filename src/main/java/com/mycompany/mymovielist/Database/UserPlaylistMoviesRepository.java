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
    
    public List<UserMovieRatingDTO> getMoviesFromPlaylist(UserPlaylist playlist, User user) {
        return entityManager.createQuery(
            "SELECT new com.mycompany.mymovielist.model.UserMovieRatingDTO(m, umr) " +
            "FROM UserPlaylistMovies upm " +
            "JOIN upm.userPlaylist pl " +
            "JOIN upm.movie m " +
            "LEFT JOIN UserMovieRating umr ON umr.movie = m AND umr.user.id = :userIdParam " +
            "WHERE pl.id = :playlistIdParam", UserMovieRatingDTO.class)
            .setParameter("playlistIdParam", playlist.getId())
            .setParameter("userIdParam", user.getId())
            .getResultList();
    }

} 
