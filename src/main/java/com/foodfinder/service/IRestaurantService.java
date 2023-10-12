package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.MenuDTO;
import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantDTO;

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

}
