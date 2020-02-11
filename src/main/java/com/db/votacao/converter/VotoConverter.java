package com.db.votacao.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.votacao.controller.dto.VotoDto;
import com.db.votacao.modelo.Voto;

import lombok.Getter;

@Component
@Getter
public class VotoConverter {

	@Autowired
	private FuncionarioConverter funcionarioConverter;
	@Autowired
	private RestauranteConverter restauranteConverter;

	@Autowired
	public VotoConverter(RestauranteConverter restauranteConverter, FuncionarioConverter funcionarioConverter) {
		this.funcionarioConverter = funcionarioConverter;
		this.restauranteConverter = restauranteConverter;
	}

	public Voto converterParaVoto(VotoDto votoDto) {
		return Voto.builder().id(votoDto.getId())
				.funcionario(funcionarioConverter.converterParaFuncionario(votoDto.getFuncionarioDto()))
				.restaurante(restauranteConverter.converterParaRestaurante(votoDto.getRestauranteDto()))
				.data(votoDto.getData()).build();
	}

	public VotoDto converterParaVotoDto(Voto voto) {
		return VotoDto.builder().id(voto.getId())
				.funcionarioDto(funcionarioConverter.converterParaFuncionarioDto(voto.getFuncionario()))
				.restauranteDto(restauranteConverter.converterParaRestauranteDto(voto.getRestaurante()))
				.voto(voto.getVoto()).data(voto.getData()).build();
	}

	public List<VotoDto> converterListaParaVotoDto(List<Voto> listaVoto) {
		List<VotoDto> listaVotoDto = new ArrayList<>();

		if (!listaVoto.isEmpty()) {
			listaVoto.forEach(voto -> {
				listaVotoDto.add(converterParaVotoDto(voto));
			});
		}
		return listaVotoDto;
	}
}
