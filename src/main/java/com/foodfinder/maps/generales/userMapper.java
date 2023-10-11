package com.foodfinder.maps.generales;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.userDTO;
import com.foodfinder.entities.user;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Clase Mapper de tipo (user) que permite mapear los datos un objeto de tipo 
 *          (userDTO) a uno de tipo (user) o viceversa.
 *          
 *          Esto ayuda a mantener el código limpio y mantenible, ya que separa las 
 *          preocupaciones de las diferentes capas de la aplicación.
 * 
 */

@Mapper
public interface userMapper {

	userMapper INSTANCE = Mappers.getMapper(userMapper.class);
	
	userDTO entityToDto(user entity);
	
	@InheritInverseConfiguration
	user dtoToEntity(userDTO dto);
}
