package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantCategoryDTO;

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
	
	public ResponseEntity<ResponseDTO> findRestaurantCategoryById(Integer id);
	
	public ResponseEntity<ResponseDTO> delete(final Integer id);
	
	public ResponseEntity<ResponseDTO> update(Integer id, RestaurantCategoryDTO restauranCategoryDTO);
	
	long countRestaurantCategoryById(Integer id);

}
