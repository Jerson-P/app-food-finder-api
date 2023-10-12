package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantDTO;
import com.foodfinder.dtos.RestaurantDetailDTO;

/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          
 *          
 */
public interface IRestaurantDetailService {

	public ResponseEntity<ResponseDTO> getRestaurantsDetail();
	
	public ResponseEntity<ResponseDTO> saveRestaurantDetail(final RestaurantDetailDTO restaurantDetail);

	
}
