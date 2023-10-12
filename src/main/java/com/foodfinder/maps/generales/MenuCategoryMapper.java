package com.foodfinder.maps.generales;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.MenuCategoryDTO;
import com.foodfinder.entities.MenuCategory;


@Mapper
public interface MenuCategoryMapper {
	
	MenuCategoryMapper INSTANCE = Mappers.getMapper(MenuCategoryMapper.class);
	
	MenuCategoryDTO entityToDto(MenuCategory entity);
	
	@InheritInverseConfiguration
	MenuCategory dtoToEntity(MenuCategoryDTO dto);
	
	List<MenuCategoryDTO> beanListToDtoList(List<MenuCategory> lista);

}
