package com.foodfinder.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.maps.generales.RestaurantDetailMapper;
import com.foodfinder.repositories.RestaurantDetailRepository;
import com.foodfinder.service.IRestaurantDetailService;
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
public class RestaurantDetailServiceImpl implements IRestaurantDetailService{
	
	private final RestaurantDetailRepository restaurantDetailRepository;
	
	/**
	 * Método que permite obtener todos los detalles de los restaurantes .
	 */
	@Override
	public ResponseEntity<ResponseDTO> getRestaurantsDetail() {
		log.info("Inicio método Obtener el detalle de los Restaurantes");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.CONSULTA_EXITOSAMENTE,
				HttpStatus.OK.value(), RestaurantDetailMapper.INSTANCE.beanListToDtoList(this.restaurantDetailRepository.findAll())),
				HttpStatus.OK);
	}

}
