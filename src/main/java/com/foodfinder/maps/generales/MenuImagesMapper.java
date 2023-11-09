package com.foodfinder.maps.generales;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import com.foodfinder.dtos.MenuImagesDTO;
import com.foodfinder.entities.MenuImages;


@Mapper
public interface MenuImagesMapper {
	
	MenuImagesMapper INSTANCE = Mappers.getMapper(MenuImagesMapper.class);
	
	MenuImagesDTO entityToDto(MenuImages entity);
	
	@InheritInverseConfiguration
	MenuImages dtoToEntity(MenuImagesDTO dto);
	
	List<MenuImagesDTO> beanListToDtoList(List<MenuImages> lista);
}
