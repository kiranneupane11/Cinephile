/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.Database;
import com.mycompany.mymovielist.repository.*;
import com.mycompany.mymovielist.model.*;

/**
 *
 * @author kiran
 */
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public abstract class DatabaseRepository<T, ID> extends AbstractRepository<T, ID> {
    protected EntityManager entityManager;
    private final Class<T> entityType;

    protected DatabaseRepository(Class<T> entityType, EntityManager entityManager) {
        this.entityType = entityType;
        this.entityManager = entityManager;

    }

    @Override
    @Transactional
    public void add(ID id, T item) {
        entityManager.getTransaction().begin(); 
        entityManager.persist(item);  
        entityManager.getTransaction().commit();
    }

    @Override
    public void remove(ID id) {
        entityManager.getTransaction().begin(); // Start a transaction
        get(id).ifPresent(entity -> entityManager.remove(entity)); // Remove the entity
        entityManager.getTransaction().commit(); // Commit the transaction
    }

    // Getting an entity by its ID from the database
    @Override
    public Optional<T> get(ID id) {
        return Optional.ofNullable(entityManager.find(entityType, id)); // Find the entity
    }

    // Getting all entities from the database
    @Override
    public List<T> getAll() {
        return entityManager.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType)
                .getResultList(); // Retrieve all entities
    }
}

