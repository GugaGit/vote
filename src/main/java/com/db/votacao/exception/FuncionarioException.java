package com.db.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FuncionarioException extends RuntimeException {

	private static final String FUNCIONARIO_NAO_FOI_ENCONTRADO = "Funcionário informado não foi encontrado, verifique o nome informado";

	private static final long serialVersionUID = 7L;

	public FuncionarioException(String message, Throwable cause) {
		super(message, cause);
	}

	public FuncionarioException() {
		super(FUNCIONARIO_NAO_FOI_ENCONTRADO);
	}
}
