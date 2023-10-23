package com.foodfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodfinder.entities.RestaurantDetail;


/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de restaurantDetailRepository
 *          
 */

@Repository
public interface RestaurantDetailRepository extends JpaRepository<RestaurantDetail, Integer>{
	
	long countRestaurantDetailById(Integer id);

}
