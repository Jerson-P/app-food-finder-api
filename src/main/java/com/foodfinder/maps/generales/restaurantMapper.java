package com.foodfinder.maps.generales;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.restaurantDTO;
import com.foodfinder.entities.restaurant;

@Mapper(uses = { restaurantDetailMapper.class, restaurantCategoryMapper.class, userMapper.class})

public interface restaurantMapper {
	
	restaurantMapper INSTANCE = Mappers.getMapper(restaurantMapper.class);
	
	restaurantDTO entityToDto(restaurant entity);
	
	@InheritInverseConfiguration
	restaurant dtoToEntity(restaurantDTO dto);

}
