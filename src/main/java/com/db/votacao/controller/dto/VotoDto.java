package com.db.votacao.controller.dto;

import java.time.LocalDate;

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
public class VotoDto {

	private Long id;
	private FuncionarioDto funcionarioDto;
	private RestauranteDto restauranteDto;
	private Integer voto;
	private LocalDate data;
}
