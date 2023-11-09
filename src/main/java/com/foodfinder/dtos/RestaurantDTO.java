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
public class RestaurantDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private String email;
	
	private String adress;
	
	private String phone;
	
	private String nit;
	
	private String status;
	
	private RestaurantCategoryDTO category;
	
	private RestaurantDetailDTO detail;
	
	private UserDTO user;

}
