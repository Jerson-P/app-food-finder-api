package com.foodfinder.maps.generales;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.RestaurantDTO;
import com.foodfinder.dtos.UserDTO;
import com.foodfinder.entities.Restaurant;
import com.foodfinder.entities.User;

@Mapper(uses = { RestaurantDetailMapper.class, RestaurantCategoryMapper.class, UserMapper.class})
public interface RestaurantMapper {
	
	RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
	
	RestaurantDTO entityToDto(Restaurant entity);
	
	@InheritInverseConfiguration
	Restaurant dtoToEntity(RestaurantDTO dto);
	
	List<RestaurantDTO> beanListToDtoList(List<Restaurant> lista);

}
