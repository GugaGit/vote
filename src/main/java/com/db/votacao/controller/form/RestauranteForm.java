package com.db.votacao.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.db.votacao.modelo.Restaurante;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteForm {

	@NotNull
	@NotEmpty
	@Length(max = 150)
	private String nome;

	public Restaurante converter() {
		return new Restaurante(nome);
	}
}
