package com.foodfinder.serviceImpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantDTO;
import com.foodfinder.entities.Restaurant;
import com.foodfinder.entities.RestaurantCategory;
import com.foodfinder.entities.RestaurantDetail;
import com.foodfinder.entities.User;
import com.foodfinder.maps.generales.RestaurantMapper;
import com.foodfinder.repositories.RestaurantCategoryRepository;
import com.foodfinder.repositories.RestaurantDetailRepository;
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
public class RestaurantServiceImpl implements IRestaurantService {

	private final RestaurantRepository restaurantRepository;

	private final RestaurantCategoryRepository restaurantCategoryRepository;

	private final RestaurantDetailRepository restaurantDetailRepository;

	private final UserRepository userRepository;

	/**
	 * Método que permite obtener todos los ususarios .
	 */
	@Override
	public ResponseEntity<ResponseDTO> getRestaurants() {
		log.info("Inicio método Obtener Restaurantes");
		ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
				.message(Constants.CONSULTA_EXITOSAMENTE)
				.objectResponse(RestaurantMapper.INSTANCE.beanListToDtoList(this.restaurantRepository.findAll()))
				.count(this.restaurantRepository.count()).build();
		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	public ResponseEntity<ResponseDTO> findRestaurantById(Integer id) {
		log.info("Inicio del método para obtener la categoria del menú por id");

		Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

		ResponseDTO responseDTO;
		if (restaurantOptional.isPresent()) {
			RestaurantDTO restaurantDTO = RestaurantMapper.INSTANCE.entityToDto(restaurantOptional.get());
			long count = restaurantRepository.countRestaurantById(id);

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.CONSULTA_EXITOSAMENTE).objectResponse(restaurantDTO).count(count).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Restaurante no encontrado para el Id: " + id).objectResponse(null).count(0L).build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	@Override
	public ResponseEntity<ResponseDTO> saveRestaurant(RestaurantDTO restaurant) {
		log.info("Inicio metodo guardar Restaurante ");
		System.out.println("Inicio metodo guardar Restaurante " + restaurant);
		this.restaurantRepository.save(RestaurantMapper.INSTANCE.dtoToEntity(restaurant));

		log.info("Fin metodo guardar restaurante");
		ResponseDTO responseDTO;
		
		Restaurant restaurantF = restaurantRepository.findByName(restaurant.getName());
		System.out.println("restaurantF " + restaurantF);
		
		RestaurantDTO lastInsertrestaurant = RestaurantMapper.INSTANCE.entityToDto(restaurantF);
		System.out.println("lastInsertrestaurant Objeto " + lastInsertrestaurant);

		responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value()).message(Constants.GUARDADO_EXITOSAMENTE)
				.objectResponse(lastInsertrestaurant).count(1L).build();

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);

	}

	@Override
	public ResponseEntity<ResponseDTO> delete(Integer id) {
		try {
			this.restaurantRepository.deleteById(id);

			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.ELIMINADO_EXITOSAMENTE, HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.NO_SE_PUEDE_ELIMINAR, HttpStatus.ACCEPTED.value()),
					HttpStatus.ACCEPTED);
		}
	}

	public long countRestaurantById(Integer id) {
		return restaurantRepository.countRestaurantById(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> update(Integer id, RestaurantDTO restaurantDTO) {
		Restaurant existingRestaurant = restaurantRepository.findById(id).orElse(null);

		ResponseDTO responseDTO;
		if (existingRestaurant != null) {
			if (restaurantDTO.getCategory() != null) {
				RestaurantCategory restaurantCategory = restaurantCategoryRepository
						.findById(restaurantDTO.getCategory().getId()).orElse(null);
				existingRestaurant.setCategory(restaurantCategory);
			}
			if (restaurantDTO.getDetail() != null) {
				RestaurantDetail restaurantDetail = restaurantDetailRepository
						.findById(restaurantDTO.getDetail().getId()).orElse(null);
				existingRestaurant.setDetail(restaurantDetail);
			}
			if (restaurantDTO.getUser() != null) {
				User user = userRepository.findById(restaurantDTO.getUser().getId()).orElse(null);
				existingRestaurant.setUser(user);
			}

			existingRestaurant
					.setName(restaurantDTO.getName() != null ? restaurantDTO.getName() : existingRestaurant.getName());
			existingRestaurant.setEmail(
					restaurantDTO.getEmail() != null ? restaurantDTO.getEmail() : existingRestaurant.getEmail());
			existingRestaurant.setAdress(
					restaurantDTO.getAdress() != null ? restaurantDTO.getAdress() : existingRestaurant.getAdress());
			existingRestaurant.setPhone(
					restaurantDTO.getPhone() != null ? restaurantDTO.getPhone() : existingRestaurant.getPhone());
			existingRestaurant
					.setNit(restaurantDTO.getNit() != null ? restaurantDTO.getNit() : existingRestaurant.getNit());

			RestaurantDTO restaurantDTOR = RestaurantMapper.INSTANCE
					.entityToDto(restaurantRepository.save(existingRestaurant));

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.ACTUALIZADO_EXITOSAMENTE).objectResponse(restaurantDTOR).count(1L).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Restaurante no encontrado con el Id: " + id).objectResponse(null).count(0L).build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);

	}

	@Override
	public Restaurant findByName(String name) {
		return restaurantRepository.findByName(name);
	}
}
