package com.db.votacao.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.db.votacao.modelo.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDto {

	@NotBlank
	@NotNull
	private Long id;
	@NotBlank
	@NotNull
	private String nome;
	private LocalDate dataVoto;

	public FuncionarioDto(Funcionario funcionario) {
		this.id = funcionario.getId();
		this.nome = funcionario.getNome();
		this.dataVoto = funcionario.getDataVoto();
	}
}