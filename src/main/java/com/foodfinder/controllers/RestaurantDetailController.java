package com.foodfinder.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.dtos.RestaurantDetailDTO;
import com.foodfinder.serviceImpl.RestaurantDetailServiceImpl;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          Controlador que expone los servicios para trabajar con objeto(s) de
 *          tipo (Restaurant).
 */

@RestController
@RequestMapping("/api/v1/restaurantDetail")
@Tag(name = "restaurantDetail - Controller", description = "Controller encargado de gestionar las operaciones del restaurantDetail.")
@CrossOrigin(origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
@RequiredArgsConstructor
public class RestaurantDetailController {
	
	/**
	 * Atributo que inyecta una instancia de la interfaz que contiene los métodos
	 * que seran usados en este controlador.
	 */
	private final RestaurantDetailServiceImpl RestaurantDetailService;
	
	//@Operation(summary = "Operación que permite consultar los detalles de los restaurantes")
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
		return this.RestaurantDetailService.getRestaurantsDetail();
	}
	
	@Operation(summary = "Operación que permite crear un restaurantDetail")
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
	public ResponseEntity<ResponseDTO> save(@RequestBody RestaurantDetailDTO restaurantDetail){
		return this.RestaurantDetailService.saveRestaurantDetail(restaurantDetail);
	}
	
	@Operation(summary = "Operación que permite eliminar los registros del Detalle de restaurante")
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
            return this.RestaurantDetailService.delete(id);
    }
	
	@Operation(summary = "Operación que permite consultar un Detalle de Restaurante a partir de un id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se consulta exitosamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = com.foodfinder.dtos.ResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getMenuId(@PathVariable Integer id) {
		return this.RestaurantDetailService.findRestaurantDetailById(id);
	}
	
	@Operation(summary = "Operación que permite actualizar el detalle de un restaurante a partir de un id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se consulta exitosamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = com.foodfinder.dtos.ResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }), })
	
	@PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable Integer id, @RequestBody RestaurantDetailDTO restauranDetail) {
        return this.RestaurantDetailService.update(id, restauranDetail);
    }
}
