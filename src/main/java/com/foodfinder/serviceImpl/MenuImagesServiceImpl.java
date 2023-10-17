package com.foodfinder.serviceImpl;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodfinder.dtos.MenuImagesDTO;
import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.entities.MenuImages;
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

		 ResponseDTO responseDTO = ResponseDTO.builder()
		            .statusCode(HttpStatus.OK.value())
		            .message(Constants.CONSULTA_EXITOSAMENTE)
		            .objectResponse(MenuImagesMapper.INSTANCE.beanListToDtoList(this.menuImagesRepository.findAll()))
		            .count(this.menuImagesRepository.count())  
		            .build();

		    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@Override
	public ResponseEntity<ResponseDTO> save(MenuImagesDTO menuImages) {
		log.info("Inicio metodo guardar menu ");
		System.out.println("Inicio metodo guardar menu "+ menuImages);
		this.menuImagesRepository.save(MenuImagesMapper.INSTANCE.dtoToEntity(menuImages));
	
		log.info("Fin metodo guardar menu");
		return new ResponseEntity<ResponseDTO>(Utils.mapearRespuesta(Constants.GUARDADO_EXITOSAMENTE, HttpStatus.CREATED.value()), HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<ResponseDTO> delete(Integer id) {
		try {
			this.menuImagesRepository.deleteById(id);

			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.ELIMINADO_EXITOSAMENTE, HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.NO_SE_PUEDE_ELIMINAR, HttpStatus.ACCEPTED.value()),
					HttpStatus.ACCEPTED);
		}
	}
	
	/**
	 * Método que permite optener las imágenes del menú por Id.
	 */
	
	@Override
	public ResponseEntity<ResponseDTO> findMenuImagesById(Integer id) {
        log.info("Inicio del método para obtener imágenes del menú por ID");

        Optional<MenuImages> menuImagesOptional = menuImagesRepository.findById(id);

        ResponseDTO responseDTO;
        if (menuImagesOptional.isPresent()) {
            MenuImagesDTO menuImagesDTO = MenuImagesMapper.INSTANCE.entityToDto(menuImagesOptional.get());
            long count = menuImagesRepository.countMenuImagesById(id);
            
            responseDTO = ResponseDTO.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message(Constants.CONSULTA_EXITOSAMENTE)
                    .objectResponse(menuImagesDTO)
                    .count(count)
                    .build();
        } else {
            responseDTO = ResponseDTO.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Imagen Menú no encontrada para el ID: " + id)
                    .objectResponse(null)
                    .count(0L)
                    .build();
        }

        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
    }

	@Override
	public long countMenuImagesById(Integer id) {
		 return menuImagesRepository.countMenuImagesById(id);
	}
	
}
