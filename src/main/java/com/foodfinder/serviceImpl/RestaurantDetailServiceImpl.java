package com.foodfinder.serviceImpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantDetailDTO;
import com.foodfinder.entities.RestaurantDetail;
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
public class RestaurantDetailServiceImpl implements IRestaurantDetailService {

	private final RestaurantDetailRepository restaurantDetailRepository;

	/**
	 * Método que permite obtener todos los detalles de los restaurantes .
	 */
	@Override
	public ResponseEntity<ResponseDTO> getRestaurantsDetail() {
		log.info("Inicio método Obtener el detalle de los Restaurantes");

		ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
				.message(Constants.CONSULTA_EXITOSAMENTE)
				.objectResponse(
						RestaurantDetailMapper.INSTANCE.beanListToDtoList(this.restaurantDetailRepository.findAll()))
				.count(this.restaurantDetailRepository.count()).build();

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@Override
	public ResponseEntity<ResponseDTO> saveRestaurantDetail(RestaurantDetailDTO restaurantDetail) {
		System.out.println("Inicio metodo guardar restaurantDetail " + restaurantDetail);
		this.restaurantDetailRepository.save(RestaurantDetailMapper.INSTANCE.dtoToEntity(restaurantDetail));
		log.info("Fin metodo guardar restaurantDetail");
		return new ResponseEntity<ResponseDTO>(
				Utils.mapearRespuesta(Constants.GUARDADO_EXITOSAMENTE, HttpStatus.CREATED.value()), HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<ResponseDTO> delete(Integer id) {
		try {
			this.restaurantDetailRepository.deleteById(id);

			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.ELIMINADO_EXITOSAMENTE, HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.NO_SE_PUEDE_ELIMINAR, HttpStatus.ACCEPTED.value()),
					HttpStatus.ACCEPTED);
		}
	}

	/**
	 * Método que permite optener el Detalle restaurante por Id.
	 */

	@Override
	public ResponseEntity<ResponseDTO> findRestaurantDetailById(Integer id) {
		log.info("Inicio del método para obtener el Detalle restaurante por id");

		Optional<RestaurantDetail> RestaurantDetailOptional = restaurantDetailRepository.findById(id);

		ResponseDTO responseDTO;
		if (RestaurantDetailOptional.isPresent()) {
			RestaurantDetailDTO RestaurantDetailDTO = RestaurantDetailMapper.INSTANCE
					.entityToDto(RestaurantDetailOptional.get());
			long count = restaurantDetailRepository.countRestaurantDetailById(id);

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.CONSULTA_EXITOSAMENTE).objectResponse(RestaurantDetailDTO).count(count).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Detalle de Restaurante no encontrado para el ID: " + id).objectResponse(null).count(0L)
					.build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	@Override
	public ResponseEntity<ResponseDTO> update(Integer id, RestaurantDetailDTO RestaurantDetailDTO) {
		RestaurantDetail existingRestaurantDetail = restaurantDetailRepository.findById(id).orElse(null);
		ResponseDTO responseDTO;

		if (existingRestaurantDetail != null) {

			existingRestaurantDetail.setName(RestaurantDetailDTO.getName() != null ? RestaurantDetailDTO.getName()
					: existingRestaurantDetail.getName());
			existingRestaurantDetail
					.setDescription(RestaurantDetailDTO.getDescription() != null ? RestaurantDetailDTO.getDescription()
							: existingRestaurantDetail.getDescription());

			RestaurantDetailDTO restaurantDetailDTO = RestaurantDetailMapper.INSTANCE
					.entityToDto(restaurantDetailRepository.save(existingRestaurantDetail));

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.ACTUALIZADO_EXITOSAMENTE).objectResponse(restaurantDetailDTO).count(1L).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Detalle del restaurante no encontrada con el Id: " + id).objectResponse(null).count(0L)
					.build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	@Override
	public long countRestaurantDetailById(Integer id) {
		return restaurantDetailRepository.countRestaurantDetailById(id);
	}

}
