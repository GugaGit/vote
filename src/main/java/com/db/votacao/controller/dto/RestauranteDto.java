package com.db.votacao.controller.dto;

import java.time.LocalDate;

import com.db.votacao.modelo.Restaurante;

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
public class RestauranteDto {

	private Long id;
	private String nome;
	private Integer voto;
	private LocalDate dataEleito;
	private Boolean elegivel;

	public RestauranteDto(Restaurante restaurante) {
		this.id = restaurante.getId();
		this.nome = restaurante.getNome();
		this.voto = restaurante.getVoto();
		this.elegivel = restaurante.getElegivel();
	}
}
