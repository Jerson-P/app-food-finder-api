package com.foodfinder.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.UserDTO;
import com.foodfinder.serviceImpl.UserServiceImpl;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Controlador que expone los servicios para trabajar con objeto(s) de
 *          tipo (user).
 */

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Usuario - Controller", description = "Controller encargado de gestionar las operaciones del usuario.")
@CrossOrigin(origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
@RequiredArgsConstructor
public class UserController {

	/**
	 * Atributo que inyecta una instancia de la interfaz que contiene los métodos
	 * que seran usados en este controlador.
	 */
	private final UserServiceImpl userService;
	
	//@Operation(summary = "Operación que permite consultar los usuarios")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se consulta exitosamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = com.foodfinder.dtos.ResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
	@GetMapping("/all")
	public ResponseEntity<ResponseDTO> obtenerHistorial() {
		return this.userService.obtenerUsuarios();
	}
	
	@Operation(summary = "Operación que permite guardar el usuario ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se consulta exitosamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = com.foodfinder.dtos.ResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
	@PostMapping("/guardar")
	public ResponseEntity<ResponseDTO> guardarUsuario(@RequestBody UserDTO user) {
		return this.userService.guardarUsuario(user);
	}
}
