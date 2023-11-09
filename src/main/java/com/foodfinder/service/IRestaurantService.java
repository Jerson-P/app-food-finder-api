package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantDTO;
import com.foodfinder.entities.Restaurant;

/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          
 *          
 */

public interface IRestaurantService {
	
	public ResponseEntity<ResponseDTO> getRestaurants();
	
	public ResponseEntity<ResponseDTO> saveRestaurant(final RestaurantDTO restaurant);
	
	public ResponseEntity<ResponseDTO> delete(final Integer id);
	
	public ResponseEntity<ResponseDTO> findRestaurantById(Integer id);
	
	public ResponseEntity<ResponseDTO> update(Integer id, RestaurantDTO restaurantDTO);
	
	long countRestaurantById(Integer id);
	
	public Restaurant findByName(String name);
}
