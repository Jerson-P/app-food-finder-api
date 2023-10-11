package com.foodfinder.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.maps.generales.RestaurantMapper;
import com.foodfinder.maps.generales.UserMapper;
import com.foodfinder.repositories.RestaurantRepository;
import com.foodfinder.repositories.UserRepository;
import com.foodfinder.service.IRestaurantService;
import com.foodfinder.utils.Constants;
import com.foodfinder.utils.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          Clase que implementa la interfaz de la lógica de negocio.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements IRestaurantService{
	
	private final RestaurantRepository restaurantRepository;
	
	/**
	 * Método que permite obtener todos los ususarios .
	 */
	@Override
	public ResponseEntity<ResponseDTO> getRestaurants(){
		log.info("Inicio método Obtener Restaurantes");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.CONSULTA_EXITOSAMENTE,
				HttpStatus.OK.value(), RestaurantMapper.INSTANCE.beanListToDtoList(this.restaurantRepository.findAll())),
				HttpStatus.OK);
	}
}
