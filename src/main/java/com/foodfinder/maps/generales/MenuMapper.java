package com.foodfinder.maps.generales;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.MenuDTO;
import com.foodfinder.entities.Menu;



@Mapper
public interface MenuMapper {
	
	MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);
	
	MenuDTO entityToDto(MenuDTO entity);
	
	@InheritInverseConfiguration
	Menu dtoToEntity(MenuDTO dto);
	
	List<MenuDTO> beanListToDtoList(List<Menu> lista);

}
