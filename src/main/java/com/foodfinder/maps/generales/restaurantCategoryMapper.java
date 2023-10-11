package com.foodfinder.maps.generales;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.RestaurantCategoryDTO;
import com.foodfinder.entities.RestaurantCategory;

@Mapper
public interface RestaurantCategoryMapper {
	
	RestaurantCategoryMapper INSTANCE = Mappers.getMapper(RestaurantCategoryMapper.class);
	
	RestaurantCategoryDTO entityToDto(RestaurantCategory entity);
	
	@InheritInverseConfiguration
	RestaurantCategory dtoToEntity(RestaurantCategoryDTO dto);

}
