package com.foodfinder.service;

import org.springframework.http.ResponseEntity;

import com.foodfinder.dtos.ResponseDTO;

public interface IUserService {

	public ResponseEntity<ResponseDTO> obtenerUsuarios();
}
