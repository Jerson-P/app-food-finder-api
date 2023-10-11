package com.foodfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodfinder.entities.restaurant;


/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de restaurantRepository
 *          
 */

@Repository
public interface restaurantRepository extends JpaRepository<restaurant, Integer>{

}
