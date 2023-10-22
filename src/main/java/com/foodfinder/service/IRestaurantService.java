package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantCategoryDTO;
import com.foodfinder.dtos.RestaurantDTO;
import com.foodfinder.dtos.RestaurantDetailDTO;
import com.foodfinder.dtos.UserDTO;

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
	
	//public ResponseEntity<ResponseDTO> update(Integer id, RestaurantDTO restaurantDTO, RestaurantCategoryDTO restaurantCategoryDTO, RestaurantDetailDTO restaurnatDetailDTO, UserDTO userDTO);
	

}
