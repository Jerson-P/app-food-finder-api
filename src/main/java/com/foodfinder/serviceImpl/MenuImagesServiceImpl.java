package com.foodfinder.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.maps.generales.MenuImagesMapper;
import com.foodfinder.repositories.MenuImagesRepository;
import com.foodfinder.service.IMenuImagesService;
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
public class MenuImagesServiceImpl implements IMenuImagesService{
	
	private final MenuImagesRepository menuImagesRepository;
	
	/**
	 * Método que permite obtener todos los Menu .
	 */
	@Override
	public ResponseEntity<ResponseDTO> getMenuImages(){
		log.info("Inicio método Obtener Menu Imagenes");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.CONSULTA_EXITOSAMENTE,
				HttpStatus.OK.value(), MenuImagesMapper.INSTANCE.beanListToDtoList(this.menuImagesRepository.findAll())),
				HttpStatus.OK);
	}
}
