package com.foodfinder.serviceImpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantCategoryDTO;
import com.foodfinder.dtos.RestaurantDTO;
import com.foodfinder.dtos.RestaurantDetailDTO;
import com.foodfinder.dtos.UserDTO;
import com.foodfinder.entities.Restaurant;
import com.foodfinder.entities.RestaurantCategory;
import com.foodfinder.entities.RestaurantDetail;
import com.foodfinder.entities.User;
import com.foodfinder.maps.generales.RestaurantCategoryMapper;
import com.foodfinder.maps.generales.RestaurantDetailMapper;
import com.foodfinder.maps.generales.RestaurantMapper;
import com.foodfinder.maps.generales.UserMapper;
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
		return new ResponseEntity<ResponseDTO>(
				Utils.mapearRespuesta(Constants.GUARDADO_EXITOSAMENTE, HttpStatus.CREATED.value()), HttpStatus.CREATED);

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

	/*@Override
	public ResponseEntity<ResponseDTO> update(Integer id, RestaurantDTO restaurantDTO, RestaurantCategoryDTO restaurantCategoryDTO, RestaurantDetailDTO restaurnatDetailDTO, UserDTO userDTO){
		Restaurant existingRestauran = restaurantRepository.findById(id).orElse(null);
		RestaurantCategory existingRestaurantCategory = restaurantCategoryRepository.findById(id).orElse(null);
		RestaurantDetail existingRestaurantDetail = restaurantDetailRepository.findById(id).orElse(null);
		User existingUser = userRepository.findById(id).orElse(null); 
		ResponseDTO responseDTO;
		
		if(existingRestauran != null && existingRestaurantCategory != null && existingRestaurantDetail != null && existingUser != null) {
			existingRestauran.setName(restaurantDTO.getName() != null ? restaurantDTO.getName() : existingRestauran.getName());
			existingRestauran.setEmail(restaurantDTO.getEmail() != null ? restaurantDTO.getEmail() : existingRestauran.getName());
			existingRestauran.setAdress(restaurantDTO.getAdress() != null ? restaurantDTO.getAdress() : existingRestauran.getAdress());
			existingRestauran.setPhone(restaurantDTO.getPhone() != null ? restaurantDTO.getPhone() : existingRestauran.getPhone());
			existingRestauran.setNit(restaurantDTO.getNit() != null ? restaurantDTO.getNit() : existingRestauran.getNit());
			existingRestaurantCategory.setName(restaurantCategoryDTO.getName() != null ? restaurantCategoryDTO.getName() : existingRestaurantCategory.getName());
			existingRestaurantCategory.setDescription(restaurantCategoryDTO.getDescription() != null ? restaurantCategoryDTO.getDescription() : existingRestaurantCategory.getDescription());
			existingRestaurantDetail.setName(restaurnatDetailDTO.getName() != null ? restaurnatDetailDTO.getName() : existingRestaurantDetail.getName());
			existingRestaurantDetail.setDescription(restaurnatDetailDTO.getDescription() != null ? restaurnatDetailDTO.getDescription() : existingRestaurantDetail.getDescription());
			existingRestaurantDetail.setOpeninHours(restaurnatDetailDTO.getOpeninHours() != null ? restaurnatDetailDTO.getOpeninHours() : existingRestaurantDetail.getOpeninHours());
			existingRestaurantDetail.setWebSite(restaurnatDetailDTO.getWebSite() != null ? restaurnatDetailDTO.getWebSite() : existingRestaurantDetail.getWebSite());
			existingRestaurantDetail.setLocationMap(restaurnatDetailDTO.getLocationMap() != null ? restaurnatDetailDTO.getLocationMap() : existingRestaurantDetail.getLocationMap());
			existingUser.setUser(userDTO.getUser() != null ? userDTO.getUser() : existingUser.getUser());
			existingUser.setPassword(userDTO.getPassword() != null ? userDTO.getPassword() : existingUser.getPassword());
			existingUser.setName(userDTO.getName() != null ? userDTO.getName() : existingUser.getName());
			existingUser.setIdentificationType(userDTO.getIdentificationType() != null ? userDTO.getIdentificationType() : existingUser.getIdentificationType());
			existingUser.setIdentification(userDTO.getIdentification() != null ? userDTO.getIdentification() : existingUser.getIdentification());
			existingUser.setMail(userDTO.getMail() != null ? userDTO.getMail() : existingUser.getMail());
			existingUser.setCellPhone(userDTO.getCellPhone() != null ? userDTO.getCellPhone() : existingUser.getCellPhone());
			
			RestaurantDTO restaurantDTOR = RestaurantMapper.INSTANCE.entityToDto(restaurantRepository.save(existingRestauran));
			RestaurantCategoryDTO restaurantCategoryDTOR = RestaurantCategoryMapper.INSTANCE.entityToDto(restaurantCategoryRepository.save(existingRestaurantCategory));
			RestaurantDetailDTO restauranDetailDTOR = RestaurantDetailMapper.INSTANCE.entityToDto(restaurantDetailRepository.save(existingRestaurantDetail));
			UserDTO userDTOR = UserMapper.INSTANCE.entityToDto(userRepository.save(existingUser));
			
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.ACTUALIZADO_EXITOSAMENTE).objectResponse(restaurantDTOR, restaurantCategoryDTOR, restauranDetailDTOR, userDTOR)
					.count(1L)
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Restaurante no encontrado con el Id: " + id).objectResponse(null).count(0L).build();
		}
		
		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}*/
}
