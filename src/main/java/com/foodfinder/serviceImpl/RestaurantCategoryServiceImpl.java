package com.foodfinder.serviceImpl;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantCategoryDTO;
import com.foodfinder.dtos.RestaurantDetailDTO;
import com.foodfinder.service.IRestaurantCategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.maps.generales.RestaurantCategoryMapper;
import com.foodfinder.maps.generales.RestaurantDetailMapper;
import com.foodfinder.maps.generales.RestaurantMapper;
import com.foodfinder.maps.generales.UserMapper;
import com.foodfinder.repositories.RestaurantCategoryRepository;
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
public class RestaurantCategoryServiceImpl implements IRestaurantCategoryService {
	
	private final RestaurantCategoryRepository restaurantCategoryRepository;
	
	/**
	 * Método que permite obtener todos las categorias de los restaurantes.
	 */
	@Override
	public ResponseEntity<ResponseDTO> getRestaurantsCategory() {
		log.info("Inicio método Obtener la Categoria de los Restaurantes");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.CONSULTA_EXITOSAMENTE,
				HttpStatus.OK.value(), RestaurantCategoryMapper.INSTANCE.beanListToDtoList(this.restaurantCategoryRepository.findAll())),
				HttpStatus.OK);
	}
	
	/**
	 * Método que permite guardar una categoria de restaurantes.
	 */
	
	@Override
	public ResponseEntity<ResponseDTO> saveRestaurantCategory(RestaurantCategoryDTO restaurantDetail) {
		System.out.println("Inicio metodo guardar restaurantDetail "+ restaurantDetail);
		this.restaurantCategoryRepository.save(RestaurantCategoryMapper.INSTANCE.dtoToEntity(restaurantDetail));
		log.info("Fin metodo guardar restaurantDetail");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.GUARDADO_EXITOSAMENTE, HttpStatus.CREATED.value()), HttpStatus.CREATED);

	}

}
