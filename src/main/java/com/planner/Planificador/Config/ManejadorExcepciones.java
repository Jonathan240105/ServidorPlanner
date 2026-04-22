package com.planner.Planificador.Config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManejadorExcepciones {
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> manejarRuntimeException(RuntimeException e) {
		Map<String, String> respuesta = new HashMap<>();

		respuesta.put("Error", e.getMessage());

		return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

	}
}
