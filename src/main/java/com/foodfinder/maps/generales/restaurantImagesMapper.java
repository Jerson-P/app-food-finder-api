package com.foodfinder.maps.generales;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.restaurantImagesDTO;
import com.foodfinder.entities.restaurantImages;

public interface restaurantImagesMapper {
	
	restaurantImagesMapper INSTANCE = Mappers.getMapper(restaurantImagesMapper.class);
	
	restaurantImagesDTO entityToDto(restaurantImages entity);
	
	@InheritInverseConfiguration
	restaurantImages dtoToEntity(restaurantImagesDTO dto);

}
