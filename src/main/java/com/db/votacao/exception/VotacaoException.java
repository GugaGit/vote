package com.db.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class VotacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VotacaoException(String message) {
		super(message);
	}
}
