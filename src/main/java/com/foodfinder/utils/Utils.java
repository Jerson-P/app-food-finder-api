package com.foodfinder.utils;

import org.springframework.stereotype.Component;

import com.foodfinder.dtos.ResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Clase que contiene el métodos y variables que son de utilidad
 *          general en la app. Ejecuta métodos (funciones) y variables comunes
 *          en la app. Reduce los errores y disminuye la cantidad de código
 *          utilizado en la app.
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class Utils {

	/**
	 * Método que permite mapear la respuesta de un servicio.
	 */
	public static ResponseDTO mapearRespuesta(final String message, final Integer statusCode, final Object objResponse) 
	{
		ResponseDTO response = new ResponseDTO();
		response.setMessage(message);
		response.setStatusCode(statusCode);
		response.setObjectResponse(objResponse);
		
		return response;
	}
	
	/**
	 * Método que permite mapear la respuesta de un servicio.
	 */
	public static ResponseDTO mapearRespuesta(final String message, final Integer statusCode) {
		ResponseDTO response = new ResponseDTO();
		response.setMessage(message);
		response.setStatusCode(statusCode);
		response.setObjectResponse(null);

		return response;
	}
}
