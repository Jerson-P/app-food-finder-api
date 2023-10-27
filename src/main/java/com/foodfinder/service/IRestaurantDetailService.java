package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantDetailDTO;
import com.foodfinder.entities.RestaurantDetail;

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
	
	public ResponseEntity<ResponseDTO> delete(final Integer id);
	
	public ResponseEntity<ResponseDTO> findRestaurantDetailById(Integer id);
	
	public ResponseEntity<ResponseDTO> update(Integer id, RestaurantDetailDTO restaurantDetailDTO);

	long countRestaurantDetailById(Integer id);
	
	public RestaurantDetail findByName(String name);
	
}
