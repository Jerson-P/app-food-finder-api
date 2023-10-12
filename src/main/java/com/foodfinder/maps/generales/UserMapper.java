package com.foodfinder.maps.generales;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.UserDTO;
import com.foodfinder.entities.User;

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
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDTO entityToDto(UserDTO entity);
	
	@InheritInverseConfiguration
	User dtoToEntity(UserDTO dto);
	
	List<UserDTO> beanListToDtoList(List<User> lista);
}
