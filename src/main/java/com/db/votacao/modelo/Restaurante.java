package com.db.votacao.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "restaurante")
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer voto;
	@Column(name = "dataeleito")
	private LocalDate dataEleito;
	private Boolean elegivel;

	public Restaurante(String nome, Integer voto, LocalDate dataEleito) {
		this.nome = nome;
		this.voto = voto;
		this.dataEleito = dataEleito;
		elegivel = Boolean.TRUE;
	}

	public Restaurante(String nome) {
		this.nome = nome;
		elegivel = Boolean.TRUE;
	}
}
