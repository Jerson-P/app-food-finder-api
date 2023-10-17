package com.foodfinder.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.MenuCategoryDTO;
import com.foodfinder.dtos.ResponseDTO;
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
public class MenuCategoryServiceImpl implements IMenuCategoryService{
	
	private final MenuCategoryRepository menuCategoryRepository;
	
	/**
	 * Método que permite obtener todos las categorias de los Menu .
	 */
	@Override
	public ResponseEntity<ResponseDTO> getMenuCategory(){
		log.info("Inicio método Obtener Categoria del Menu");
		
		 ResponseDTO responseDTO = ResponseDTO.builder()
		            .statusCode(HttpStatus.OK.value())
		            .message(Constants.CONSULTA_EXITOSAMENTE)
		            .objectResponse(MenuCategoryMapper.INSTANCE.beanListToDtoList(this.menuCategoryRepository.findAll()))
		            .count(this.menuCategoryRepository.count())  
		            .build();

		    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@Override
	public ResponseEntity<ResponseDTO> save(MenuCategoryDTO menuCategory) {
		log.info("Inicio metodo guardar categoria menu ");
		System.out.println("Inicio metodo guardar menu "+ menuCategory);
		this.menuCategoryRepository.save(MenuCategoryMapper.INSTANCE.dtoToEntity(menuCategory));
	
		log.info("Fin metodo guardar categoria menu");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.GUARDADO_EXITOSAMENTE, HttpStatus.CREATED.value()), HttpStatus.CREATED);

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
}
