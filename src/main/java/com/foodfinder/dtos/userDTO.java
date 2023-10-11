package com.foodfinder.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class userDTO {

	private Integer id;
	private String user;
	private String password;
	private String name;
	private String identificationType;
	private Integer identification;
	private String mail;
	private String cellPhone;
	private Boolean status;
}
