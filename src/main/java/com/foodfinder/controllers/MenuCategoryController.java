package com.foodfinder.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodfinder.dtos.MenuCategoryDTO;
import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.serviceImpl.MenuCategoryServiceImpl;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

/**
 * @author Luis Montoya
 * @version 1.0
 * 
 *          Controlador que expone los servicios para trabajar con objeto(s) de
 *          tipo (Categoria Menu).
 */

@RestController
@RequestMapping("/api/v1/menuCategory")
@Tag(name = "MenuCategory - Controller", description = "Controller encargado de gestionar las operaciones de las Categorias del Menu.")
@CrossOrigin(origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
@RequiredArgsConstructor
public class MenuCategoryController {
	
	/**
	 * Atributo que inyecta una instancia de la interfaz que contiene los métodos
	 * que seran usados en este controlador.
	 */
	private final MenuCategoryServiceImpl menuCategoryServiceImpl;
	
	@Operation(summary = "Operación que permite consultar la Categoria del menu")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se consulta exitosamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = com.foodfinder.dtos.ResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
	@GetMapping("/all")
	public ResponseEntity<ResponseDTO> obtenerTodo() {
		return this.menuCategoryServiceImpl.getMenuCategory();
	}
	
	@Operation(summary = "Operación que permite crear la categoria del menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado satisfactoriamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))}),
    })
	@PostMapping
	public ResponseEntity<ResponseDTO> save(@RequestBody MenuCategoryDTO menuCategory){
		return this.menuCategoryServiceImpl.save(menuCategory);
	}
	
	@Operation(summary = "Operación que permite eliminar los registros de categoria menu")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha procesado exitosamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
	@DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Integer id) {
            return this.menuCategoryServiceImpl.delete(id);
    }
	
	@Operation(summary = "Operación que permite consultar una categoria del menú a partir de un id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se consulta exitosamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = com.foodfinder.dtos.ResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getMenuCategoryId(@PathVariable Integer id) {
		return this.menuCategoryServiceImpl.findMenuCategoryById(id);
	}


}
