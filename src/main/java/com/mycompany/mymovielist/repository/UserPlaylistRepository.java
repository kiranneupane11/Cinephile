/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.repository;

/**
 *
 * @author kiran
 */
import com.mycompany.mymovielist.model.*;
import jakarta.persistence.EntityManager;
import java.util.*;


public class UserPlaylistRepository extends DatabaseRepository<UserPlaylist, Long> {
    public UserPlaylistRepository(EntityManager entityManager) {
        super(UserPlaylist.class, entityManager);
    }
    
    public List<UserPlaylist> getListsByUserId(User user){
        return entityManager.createQuery("SELECT ml FROM UserPlaylist ml WHERE ml.userId = :user", UserPlaylist.class)
            .setParameter("user", user)
            .getResultList();
    }
    
    public Optional<UserPlaylist> findByUserIdAndListName(UserPlaylist movieList) {
    return entityManager.createQuery(
        "SELECT ml FROM UserPlaylist ml WHERE ml.userId = :userId AND ml.listName = :listName",
        UserPlaylist.class)
        .setParameter("userId", movieList.getUserID())
        .setParameter("listName", movieList.getListName())
        .getResultStream()
        .findFirst();
    }
}
