package com.foodfinder.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.maps.generales.userMapper;
import com.foodfinder.repositories.userRepository;
import com.foodfinder.service.IUserService;
import com.foodfinder.utils.Utils;
import com.foodfinder.utils.constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Clase que implementa la interfaz de la lógica de negocio.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class userServiceImpl implements IUserService{

	private final userRepository userRepository;
	
	/**
	 * Método que permite obtener todos los ususarios .
	 */
	@Override
	public ResponseEntity<ResponseDTO> obtenerUsuarios(){
		log.info("Inicio método Obtener Users");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(constants.CONSULTA_EXITOSAMENTE,
				HttpStatus.OK.value(), userMapper.INSTANCE.beanListToDtoList(this.userRepository.findAll())),
				HttpStatus.OK);
	}

}
