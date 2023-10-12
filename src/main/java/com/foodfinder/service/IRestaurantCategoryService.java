package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantCategoryDTO;
import com.foodfinder.dtos.RestaurantDetailDTO;

/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          
 *          
 */
public interface IRestaurantCategoryService {
	
	public ResponseEntity<ResponseDTO> getRestaurantsCategory();
	
	public ResponseEntity<ResponseDTO> saveRestaurantCategory(final RestaurantCategoryDTO restaurantCategory);

}
