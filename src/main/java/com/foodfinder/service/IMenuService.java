package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.MenuDTO;
import com.foodfinder.dtos.ResponseDTO;

/**
 * @author Luis Montoya
 * @version 1.0
 * 
 *          
 *          
 */

public interface IMenuService {
	
	public ResponseEntity<ResponseDTO> getMenu();
	
	public ResponseEntity<ResponseDTO> save(final MenuDTO menu);
	
	public ResponseEntity<ResponseDTO> delete(final Integer id);
	
	/**
	 * Método que permite optener el menú por Id.
	 */
	public ResponseEntity<ResponseDTO> findMenuById(Integer id);

	long countMenuById(Integer id);
}
