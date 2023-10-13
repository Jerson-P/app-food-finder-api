package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.MenuCategoryDTO;
import com.foodfinder.dtos.ResponseDTO;

/**
 * @author Luis Montoya
 * @version 1.0
 * 
 *          
 *          
 */

public interface IMenuCategoryService {
	
	public ResponseEntity<ResponseDTO> getMenuCategory();
	
    public ResponseEntity<ResponseDTO> save(final MenuCategoryDTO menuCategory);
	
	public ResponseEntity<ResponseDTO> delete(final Integer id);

}
