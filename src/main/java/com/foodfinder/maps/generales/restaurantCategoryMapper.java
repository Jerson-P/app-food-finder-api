package com.foodfinder.maps.generales;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.restaurantCategoryDTO;
import com.foodfinder.entities.restaurantCategory;

public interface restaurantCategoryMapper {
	
	restaurantCategoryMapper INSTANCE = Mappers.getMapper(restaurantCategoryMapper.class);
	
	restaurantCategoryDTO entityToDto(restaurantCategory entity);
	
	@InheritInverseConfiguration
	restaurantCategory dtoToEntity(restaurantCategoryDTO dto);

}
