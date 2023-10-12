package com.foodfinder.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.CONSULTA_EXITOSAMENTE,
				HttpStatus.OK.value(), MenuCategoryMapper.INSTANCE.beanListToDtoList(this.menuCategoryRepository.findAll())),
				HttpStatus.OK);
	}
}
