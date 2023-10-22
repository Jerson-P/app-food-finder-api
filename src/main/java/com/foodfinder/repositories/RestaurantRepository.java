package com.foodfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodfinder.entities.Restaurant;


/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de restaurantRepository
 *          
 */

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

	long countRestaurantById(Integer id);
}
