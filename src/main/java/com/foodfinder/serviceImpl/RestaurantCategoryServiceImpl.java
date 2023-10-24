package com.foodfinder.serviceImpl;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantCategoryDTO;
import com.foodfinder.entities.RestaurantCategory;
import com.foodfinder.service.IRestaurantCategoryService;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foodfinder.maps.generales.RestaurantCategoryMapper;
import com.foodfinder.repositories.RestaurantCategoryRepository;
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
		log.info("Inicio método Obtener el detalle de los Restaurantes");

		ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
				.message(Constants.CONSULTA_EXITOSAMENTE)
				.objectResponse(RestaurantCategoryMapper.INSTANCE
						.beanListToDtoList(this.restaurantCategoryRepository.findAll()))
				.count(this.restaurantCategoryRepository.count()).build();

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	/**
	 * Método que permite guardar una categoria de restaurantes.
	 */

	@Override
	public ResponseEntity<ResponseDTO> saveRestaurantCategory(RestaurantCategoryDTO restaurantCategoryDTO) {
		System.out.println("Inicio metodo guardar restaurantDetail " + restaurantCategoryDTO);
		this.restaurantCategoryRepository.save(RestaurantCategoryMapper.INSTANCE.dtoToEntity(restaurantCategoryDTO));
		ResponseDTO responseDTO;

		log.info("Fin metodo guardar restaurantDetail");

		RestaurantCategory lastInsertedEntity = restaurantCategoryRepository.findLastInsertedRestaurantCategory();
		RestaurantCategoryDTO lastInsertCategory = RestaurantCategoryMapper.INSTANCE.entityToDto(lastInsertedEntity);

		responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value()).message(Constants.GUARDADO_EXITOSAMENTE)
				.objectResponse(lastInsertCategory).count(1L).build();

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);

	}

	public ResponseEntity<ResponseDTO> findRestaurantCategoryById(Integer id) {
		log.info("Inicio del método para obtener la categoria del restaurante por id");

		Optional<RestaurantCategory> restaurantCategoryOptional = restaurantCategoryRepository.findById(id);

		ResponseDTO responseDTO;
		if (restaurantCategoryOptional.isPresent()) {
			RestaurantCategoryDTO restaurantCategoryDTO = RestaurantCategoryMapper.INSTANCE
					.entityToDto(restaurantCategoryOptional.get());
			long count = restaurantCategoryRepository.countRestaurantCategoryById(id);

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.CONSULTA_EXITOSAMENTE).objectResponse(restaurantCategoryDTO).count(count)
					.build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Categoría del restaurante no encontrada para el Id: " + id).objectResponse(null).count(0L)
					.build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	@Override
	public ResponseEntity<ResponseDTO> delete(Integer id) {
		try {
			log.info("Inicio del método para eliminar una categoria del restaurante por id");
			this.restaurantCategoryRepository.deleteById(id);

			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.ELIMINADO_EXITOSAMENTE, HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.NO_SE_PUEDE_ELIMINAR, HttpStatus.ACCEPTED.value()),
					HttpStatus.ACCEPTED);
		}
	}

	@Override
	public ResponseEntity<ResponseDTO> update(Integer id, RestaurantCategoryDTO restaurantCategoryDTO) {
		RestaurantCategory existingRestaurantCategory = restaurantCategoryRepository.findById(id).orElse(null);
		ResponseDTO responseDTO;

		if (existingRestaurantCategory != null) {

			existingRestaurantCategory.setName(restaurantCategoryDTO.getName() != null ? restaurantCategoryDTO.getName()
					: existingRestaurantCategory.getName());
			existingRestaurantCategory.setDescription(
					restaurantCategoryDTO.getDescription() != null ? restaurantCategoryDTO.getDescription()
							: existingRestaurantCategory.getDescription());

			RestaurantCategoryDTO restaurantCategoryDTOR = RestaurantCategoryMapper.INSTANCE
					.entityToDto(restaurantCategoryRepository.save(existingRestaurantCategory));

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.ACTUALIZADO_EXITOSAMENTE).objectResponse(restaurantCategoryDTOR).count(1L)
					.build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Categoría del restaurante no encontrada con el Id: " + id).objectResponse(null).count(0L)
					.build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	@Override
	public long countRestaurantCategoryById(Integer id) {
		return restaurantCategoryRepository.countRestaurantCategoryById(id);
	}

}
