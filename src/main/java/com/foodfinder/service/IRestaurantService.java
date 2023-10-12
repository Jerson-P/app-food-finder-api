package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;

/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          
 *          
 */

public interface IRestaurantService {
	
	public ResponseEntity<ResponseDTO> getRestaurants();

}
