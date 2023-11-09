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
public class UserServiceImpl implements IUserService {

	private final UserRepository userRepository;

	/**
	 * Método que permite obtener todos los ususarios .
	 */
	@Override
	public ResponseEntity<ResponseDTO> obtenerUsuarios() {
		log.info("Inicio método Obtener Users");

		ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
				.message(Constants.CONSULTA_EXITOSAMENTE)
				.objectResponse(UserMapper.INSTANCE.beanListToDtoList(this.userRepository.findAll()))
				.count(this.userRepository.count()).build();

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	/**
	 * Método que permite optener los usuario por Id.
	 */

	@Override
	public ResponseEntity<ResponseDTO> findUserById(Integer id) {
		log.info("Inicio metodo para obtener usuario por id");

		Optional<User> userOptional = userRepository.findById(id);

		ResponseDTO responseDTO;
		if (userOptional.isPresent()) {
			UserDTO userDTO = UserMapper.INSTANCE.entityToDto(userOptional.get());
			long count = userRepository.countUserById(id);

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.CONSULTA_EXITOSAMENTE).objectResponse(userDTO).count(count).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("El Usuario con Id " + id + " no se encuentra.").objectResponse(null).count(0L).build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

	/**
	 * Método que permite registrar un usuario.
	 */
	@Override
	public ResponseEntity<ResponseDTO> save(UserDTO user) {
		boolean permiteGuardar = false;
		List<User> listUsuario = this.userRepository.findAll();
		System.out.println("guardar usuario -> " + user);
		if (Objects.nonNull(user.getUser())) {
			for (User u : listUsuario) {
				if (Objects.nonNull(listUsuario)) {
					permiteGuardar = true;
				} else {
					permiteGuardar = false;
					break;
				}
			}
		}
		if (permiteGuardar) {
			log.info("Inicio método de guardar usuario");

			user.setId(user.getId());
			user.setPassword(user.getPassword());
			this.userRepository.save(UserMapper.INSTANCE.dtoToEntity(user));
			log.info("Fin metodo de guardar usuario");

			ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.GUARDADO_EXITOSAMENTE).objectResponse(null).count(1L).build();

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		} else {

			ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.ACCEPTED.value())
					.message(Constants.USUARIO_NO_PUEDE_GUARDAR).objectResponse(null).count(0L).build();

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
		}
	}

	@Override
	public ResponseEntity<ResponseDTO> deleteUser(Integer id) {
		try {
			log.info("Inicio método de eliminar Usuario");
			this.userRepository.deleteById(id);

			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.ELIMINADO_EXITOSAMENTE, HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.NO_SE_PUEDE_ELIMINAR, HttpStatus.ACCEPTED.value()),
					HttpStatus.ACCEPTED);
		}
	}

	@Override
	public ResponseEntity<ResponseDTO> update(Integer id, UserDTO userDTO) {
		User existingUser = userRepository.findById(id).orElse(null);
		ResponseDTO responseDTO;

		if (existingUser != null) {
			existingUser.setUser(userDTO.getUser() != null ? userDTO.getUser() : existingUser.getUser());
			existingUser
					.setPassword(userDTO.getPassword() != null ? userDTO.getPassword() : existingUser.getPassword());
			existingUser.setName(userDTO.getName() != null ? userDTO.getName() : existingUser.getName());
			existingUser.setIdentificationType(userDTO.getIdentificationType() != null ? userDTO.getIdentificationType()
					: existingUser.getIdentificationType());
			existingUser.setIdentification(userDTO.getIdentification() != null ? userDTO.getIdentification()
					: existingUser.getIdentification());
			existingUser.setMail(userDTO.getMail() != null ? userDTO.getMail() : existingUser.getMail());
			existingUser.setCellPhone(
					userDTO.getCellPhone() != null ? userDTO.getCellPhone() : existingUser.getCellPhone());

			UserDTO userDTOR = UserMapper.INSTANCE.entityToDto(userRepository.save(existingUser));

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.ACTUALIZADO_EXITOSAMENTE).objectResponse(userDTOR).count(1L).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Usuario no encontrado para el Id: " + id).objectResponse(null).count(0L).build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}

}
