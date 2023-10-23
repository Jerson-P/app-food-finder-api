package com.foodfinder.serviceImpl;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.MenuDTO;
import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.entities.Menu;
import com.foodfinder.entities.MenuCategory;
import com.foodfinder.entities.Restaurant;
import com.foodfinder.maps.generales.MenuMapper;
import com.foodfinder.repositories.MenuCategoryRepository;
import com.foodfinder.repositories.MenuRepository;
import com.foodfinder.repositories.RestaurantRepository;
import com.foodfinder.service.IMenuService;
import com.foodfinder.utils.Constants;
import com.foodfinder.utils.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Luis Montoya
 * @version 1.0
 * 
 *          Clase que implementa la interfaz de la lógica de negocio.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements IMenuService{
	
	private final MenuRepository menuRepository;
	
	private final RestaurantRepository restaurantRepository;
	
	private final MenuCategoryRepository menuCategoryRepository;

	/**
	 * Método que permite obtener todos los Menu .
	 */
	@Override
	public ResponseEntity<ResponseDTO> getMenu(){
		log.info("Inicio método Obtener Menu");

		 ResponseDTO responseDTO = ResponseDTO.builder()
		            .statusCode(HttpStatus.OK.value())
		            .message(Constants.CONSULTA_EXITOSAMENTE)
		            .objectResponse(MenuMapper.INSTANCE.beanListToDtoList(this.menuRepository.findAll()))
		            .count(this.menuRepository.count())  
		            .build();

		    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@Override
	public ResponseEntity<ResponseDTO> save(MenuDTO menu) {
		log.info("Inicio metodo guardar menu ");
		System.out.println("Inicio metodo guardar menu "+ menu);
		this.menuRepository.save(MenuMapper.INSTANCE.dtoToEntity(menu));
	
		log.info("Fin metodo guardar menu");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.GUARDADO_EXITOSAMENTE, HttpStatus.CREATED.value()), HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<ResponseDTO> delete(Integer id) {
		try {
			this.menuRepository.deleteById(id);

			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.ELIMINADO_EXITOSAMENTE, HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.NO_SE_PUEDE_ELIMINAR, HttpStatus.ACCEPTED.value()),
					HttpStatus.ACCEPTED);
		}
	}

	@Override
	public ResponseEntity<ResponseDTO> findMenuById(Integer id) {
		   log.info("Inicio del método para obtener el menú por id");

	        Optional<Menu> menuOptional = menuRepository.findById(id);

	        ResponseDTO responseDTO;
	        if (menuOptional.isPresent()) {
	            MenuDTO menuDTO = MenuMapper.INSTANCE.entityToDto(menuOptional.get());
	            long count = menuRepository.countMenuById(id);
	            
	            responseDTO = ResponseDTO.builder()
	                    .statusCode(HttpStatus.OK.value())
	                    .message(Constants.CONSULTA_EXITOSAMENTE)
	                    .objectResponse(menuDTO)
	                    .count(count)
	                    .build();
	        } else {
	            responseDTO = ResponseDTO.builder()
	                    .statusCode(HttpStatus.NOT_FOUND.value())
	                    .message("Menú no encontrado para el ID: " + id)
	                    .objectResponse(null)
	                    .count(0L)
	                    .build();
	        }

	        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	@Override
	public long countMenuById(Integer id) {
		 return menuRepository.countMenuById(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> update(Integer id, MenuDTO menu) {
		Menu existingMenu = menuRepository.findById(id).orElse(null);
		
		ResponseDTO responseDTO;
		
        if (existingMenu != null) {
        	if(menu.getRestaurant() !=null) {
    			Restaurant restaurant = restaurantRepository.findById(menu.getRestaurant().getId()).orElse(null);
    			existingMenu.setRestaurant(restaurant);
        	}
        	
        	if(menu.getCategory() !=null) {
    			MenuCategory category = menuCategoryRepository.findById(menu.getCategory().getId()).orElse(null);
    			existingMenu.setCategory(category);
        	}
        	
        	existingMenu.setName(menu.getName() != null ? menu.getName() : existingMenu.getName());
        	existingMenu.setDescription(menu.getDescription() != null ? menu.getDescription() : existingMenu.getDescription());
        	existingMenu.setPrice(menu.getPrice() != 0 ? menu.getPrice() : existingMenu.getPrice());
        	existingMenu.setAvailability(menu.getAvailability() != null ? menu.getAvailability() : existingMenu.getAvailability());

        	
            MenuDTO menuDTOR = MenuMapper.INSTANCE.entityToDto(menuRepository.save(existingMenu));
            
            responseDTO = ResponseDTO.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message(Constants.ACTUALIZADO_EXITOSAMENTE)
                    .objectResponse(menuDTOR)
                    .count(1L)
                    .build();
        } else {
            responseDTO = ResponseDTO.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Menú no encontrado para el Id: " + id)
                    .objectResponse(null)
                    .count(0L)
                    .build();
        }

        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}
	
}
