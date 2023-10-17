package com.foodfinder.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.MenuDTO;
import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.maps.generales.MenuMapper;
import com.foodfinder.repositories.MenuRepository;
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
	
}
