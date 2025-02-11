/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.repository;
import java.util.*;
/**
 *
 * @author kiran
 */
public class GenericRepository<T> {
    List<T> items = new ArrayList<>();
    
    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }
    
}
