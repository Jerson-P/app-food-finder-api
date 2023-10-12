package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;

/**
 * @author Luis Montoya
 * @version 1.0
 * 
 *          
 *          
 */

public interface IMenuImagesService {
	
	public ResponseEntity<ResponseDTO> getMenuImages();

}
