package com.foodfinder.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.UserDTO;
import com.foodfinder.entities.User;
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
	
	/**
	 * Método que permite optener los usuario por Id.
	 */
	
	@Override
	public ResponseEntity<ResponseDTO> findUserById(Integer id) {
		log.info("Inicio metodo para obtener usuario por id");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.CONSULTA_EXITOSAMENTE,
				HttpStatus.OK.value(), this.userRepository.findById(id)),
				HttpStatus.OK);
	}
	
	/**
	 * Método que permite registrar un usuario.
	 */
	@Override
	public ResponseEntity<ResponseDTO> save(UserDTO user){
		boolean permiteGuardar = false;
		List<User> listUsuario = this.userRepository.findAll();
		System.out.println("guardar usuario -> "+ user);
		if(Objects.nonNull(user.getUser())) {
			for(User u : listUsuario) {
				if(Objects.nonNull(listUsuario)) {
					permiteGuardar = true;
				} else {
					permiteGuardar = false;
					break;
				}
			}
		}
		if(permiteGuardar) {
			log.info("Inicio método de guardar usuario");
			
			/*if(user.getId() != null) {
				Optional<User> usuarioTemp = this.userRepository.findById(user.getId());
				
				if(!usuarioTemp.get().getPassword().equals(user.getPassword())) {
					user.setPassword(user.getPassword());
					this.userRepository.save(UserMapper.INSTANCE.dtoToEntity(user));
					return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.ACTUALIZADO_EXITOSAMENTE, HttpStatus.CREATED.value()), HttpStatus.CREATED);
				} else {
					String clave = user.getPassword();
					user.setPassword(clave);
					this.userRepository.save(UserMapper.INSTANCE.dtoToEntity(user));
					log.info("Fin del metodo editar usuario");
					return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.ACTUALIZADO_EXITOSAMENTE, HttpStatus.CREATED.value()), HttpStatus.CREATED);
				}
			}*/
			user.setId(user.getId());
			user.setPassword(user.getPassword());
			this.userRepository.save(UserMapper.INSTANCE.dtoToEntity(user));
			log.info("Fin metodo de guardar usuario");
			return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.GUARDADO_EXITOSAMENTE, HttpStatus.CREATED.value()),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.USUARIO_NO_PUEDE_GUARDAR, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
