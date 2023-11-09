package com.foodfinder.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.foodfinder.dtos.ResponseDTO;
import com.foodfinder.utils.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	/**
	 * Método que valida estas posibles excepciones {ConstraintViolationException.class}
	 */
	@ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
	public ResponseEntity<ResponseDTO> contraintViolationError(ConstraintViolationException ex) {
		log.error("contraintViolationError: {}", ex);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(ResponseDTO.builder().objectResponse(null).statusCode(HttpStatus.ACCEPTED.value())
						.message(Constants.POR_FAVOR_VALIDAR_DATOS_NO_PUEDE_DUPLICAR_VALORES)
						.build());
	}
}
