package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.UserDTO;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Esta interfaz es la capa intermedia entre la capa de presentación y
 *          la capa de acceso a datos. Esta oculta los detalles de
 *          implementación de la capa de acceso a datos.
 * 
 */

public interface IUserService {

	public ResponseEntity<ResponseDTO> obtenerUsuarios();
	
	public ResponseEntity<ResponseDTO> save(final UserDTO user);
}
