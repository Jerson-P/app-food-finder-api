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
public class restaurantDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private String email;
	
	private String adress;
	
	private String phone;
	
	private Integer nit;
	
	private String status;
	
	private restaurantCategoryDTO category;
	
	private restaurantDetailDTO detail;
	
	private userDTO user;

}
