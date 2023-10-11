package com.foodfinder.maps.generales;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.restaurantDetailDTO;
import com.foodfinder.entities.restaurantDetail;

public interface restaurantDetailMapper {
	
	restaurantDetailMapper INSTANCE = Mappers.getMapper(restaurantDetailMapper.class);
	
	restaurantDetailDTO entityToDto(restaurantDetail entity);
	
	@InheritInverseConfiguration
	restaurantDetail dtoToEntity(restaurantDetailDTO dto);


}
