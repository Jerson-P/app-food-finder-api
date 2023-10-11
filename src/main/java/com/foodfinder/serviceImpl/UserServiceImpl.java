package com.foodfinder.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.maps.generales.UserMapper;
import com.foodfinder.repositories.UserRepository;
import com.foodfinder.service.IUserService;
import com.foodfinder.utils.Utils;
import com.foodfinder.utils.Constants;

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
public class UserServiceImpl implements IUserService{

	private final UserRepository userRepository;
	
	/**
	 * Método que permite obtener todos los ususarios .
	 */
	@Override
	public ResponseEntity<ResponseDTO> obtenerUsuarios(){
		log.info("Inicio método Obtener Users");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.CONSULTA_EXITOSAMENTE,
				HttpStatus.OK.value(), UserMapper.INSTANCE.beanListToDtoList(this.userRepository.findAll())),
				HttpStatus.OK);
	}

}
