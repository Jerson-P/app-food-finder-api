package com.foodfinder.maps.generales;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.RestaurantImagesDTO;
import com.foodfinder.entities.RestaurantImages;

public interface RestaurantImagesMapper {
	
	RestaurantImagesMapper INSTANCE = Mappers.getMapper(RestaurantImagesMapper.class);
	
	RestaurantImagesDTO entityToDto(RestaurantImages entity);
	
	@InheritInverseConfiguration
	RestaurantImages dtoToEntity(RestaurantImagesDTO dto);

}
