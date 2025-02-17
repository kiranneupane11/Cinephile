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

public interface CrudRepository<T>{
    void add(T item);
    void remove(T item);
    List<T> getAll();
    List<T> sort(Comparator<T> comparator);
    List<T> filter(Comparator<T> comparator);

}
        
        
        
public abstract class AbstractRepository implements CrudRepository{
    
}
