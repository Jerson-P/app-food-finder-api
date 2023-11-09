package com.foodfinder.serviceImpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.MenuCategoryDTO;
import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.entities.MenuCategory;
import com.foodfinder.maps.generales.MenuCategoryMapper;
import com.foodfinder.repositories.MenuCategoryRepository;
import com.foodfinder.service.IMenuCategoryService;
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
public class MenuCategoryServiceImpl implements IMenuCategoryService {

	private final MenuCategoryRepository menuCategoryRepository;

	/**
	 * Método que permite obtener todos las categorias de los Menu .
	 */
	@Override
	public ResponseEntity<ResponseDTO> getMenuCategory() {
		log.info("Inicio método Obtener Categoria del Menu");

		ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
				.message(Constants.CONSULTA_EXITOSAMENTE)
				.objectResponse(MenuCategoryMapper.INSTANCE.beanListToDtoList(this.menuCategoryRepository.findAll()))
				.count(this.menuCategoryRepository.count()).build();

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@Override
	public ResponseEntity<ResponseDTO> save(MenuCategoryDTO menuCategory) {
		log.info("Inicio metodo guardar categoria menu ");
		System.out.println("Inicio metodo guardar menu " + menuCategory);
		this.menuCategoryRepository.save(MenuCategoryMapper.INSTANCE.dtoToEntity(menuCategory));

		log.info("Fin metodo guardar categoria menu");
		ResponseDTO responseDTO;
		
		MenuCategory MenuCategoryF = menuCategoryRepository.findByName(menuCategory.getName());
		System.out.println("MenuCategoryF " + MenuCategoryF);
		
		MenuCategoryDTO lastInsertMenuCategory = MenuCategoryMapper.INSTANCE.entityToDto(MenuCategoryF);
		System.out.println("lastInsertMenuCategory Objeto " + lastInsertMenuCategory);

		responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value()).message(Constants.GUARDADO_EXITOSAMENTE)
				.objectResponse(lastInsertMenuCategory).count(1L).build();

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	@Override
	public ResponseEntity<ResponseDTO> delete(Integer id) {
		try {
			log.info("Inicio metodo eliminar categoria menu");
			this.menuCategoryRepository.deleteById(id);

			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.ELIMINADO_EXITOSAMENTE, HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.NO_SE_PUEDE_ELIMINAR, HttpStatus.ACCEPTED.value()),
					HttpStatus.ACCEPTED);
		}
	}

	@Override
	public ResponseEntity<ResponseDTO> findMenuCategoryById(Integer id) {
		log.info("Inicio del método para obtener la categoria del menú por id");

		Optional<MenuCategory> menuCategoryOptional = menuCategoryRepository.findById(id);

		ResponseDTO responseDTO;
		if (menuCategoryOptional.isPresent()) {
			MenuCategoryDTO menuCategoryDTO = MenuCategoryMapper.INSTANCE.entityToDto(menuCategoryOptional.get());
			long count = menuCategoryRepository.countMenuCategoryById(id);

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.CONSULTA_EXITOSAMENTE).objectResponse(menuCategoryDTO).count(count).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Imagen Menú no encontrada para el ID: " + id).objectResponse(null).count(0L).build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	@Override
	public long countMenuCategoryById(Integer id) {
		return menuCategoryRepository.countMenuCategoryById(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> update(Integer id, MenuCategoryDTO menuCategoryDTO) {
		MenuCategory existingMenuCategory = menuCategoryRepository.findById(id).orElse(null);
		ResponseDTO responseDTO;

		if (existingMenuCategory != null) {

			existingMenuCategory.setName(
					menuCategoryDTO.getName() != null ? menuCategoryDTO.getName() : existingMenuCategory.getName());
			existingMenuCategory
					.setDescription(menuCategoryDTO.getDescription() != null ? menuCategoryDTO.getDescription()
							: existingMenuCategory.getDescription());

			MenuCategoryDTO menuCategoryDTOR = MenuCategoryMapper.INSTANCE
					.entityToDto(menuCategoryRepository.save(existingMenuCategory));

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.ACTUALIZADO_EXITOSAMENTE).objectResponse(menuCategoryDTOR).count(1L).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Categoria del Menú no encontrada para el Id: " + id).objectResponse(null).count(0L)
					.build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	@Override
	public MenuCategory findByName(String name) {
		return menuCategoryRepository.findByName(name);
	}

}
