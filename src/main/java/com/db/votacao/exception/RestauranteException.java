package com.db.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RestauranteException extends RuntimeException {

	private static final long serialVersionUID = 7L;
	
	private static final String RESTAURANTE_NAO_ENCONTRADO = "Restaurante não encontrado.";

	public RestauranteException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestauranteException() {
		super(RESTAURANTE_NAO_ENCONTRADO);
	}
}
