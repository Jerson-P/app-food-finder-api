package com.foodfinder.maps.generales;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.foodfinder.dtos.RestaurantCategoryDTO;
import com.foodfinder.dtos.RestaurantDetailDTO;
import com.foodfinder.entities.RestaurantCategory;
import com.foodfinder.entities.RestaurantDetail;

@Mapper
public interface RestaurantDetailMapper {
	
	RestaurantDetailMapper INSTANCE = Mappers.getMapper(RestaurantDetailMapper.class);
	
	RestaurantDetailDTO entityToDto(RestaurantDetail entity);
	
	@InheritInverseConfiguration
	RestaurantDetail dtoToEntity(RestaurantDetailDTO dto);
	
	List<RestaurantDetailDTO> beanListToDtoList(List<RestaurantDetail> lista);

}
