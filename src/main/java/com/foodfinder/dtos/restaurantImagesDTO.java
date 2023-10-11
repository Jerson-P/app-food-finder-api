package com.foodfinder.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantImagesDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private byte[] image;
	
	private RestaurantDTO restaurant;
}
