package com.db.votacao.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.db.votacao.controller.dto.RestauranteDto;
import com.db.votacao.modelo.Restaurante;

@Component
public class RestauranteConverter {

	public RestauranteDto converterParaRestauranteDto(Restaurante restaurante) {
		return RestauranteDto.builder().id(restaurante.getId()).nome(restaurante.getNome())
				.dataEleito(restaurante.getDataEleito()).elegivel(restaurante.getElegivel()).voto(restaurante.getVoto())
				.build();
	}

	public Restaurante converterParaRestaurante(RestauranteDto restauranteDto) {
		return Restaurante.builder().id(restauranteDto.getId()).nome(restauranteDto.getNome())
				.dataEleito(restauranteDto.getDataEleito()).elegivel(restauranteDto.getElegivel()).build();
	}

	public List<RestauranteDto> converterParaListaRestauranteDto(List<Restaurante> restaurantes) {
		List<RestauranteDto> restaurantesDto = new ArrayList<>();
		restaurantes.forEach(restaurante -> {
			restaurantesDto.add(converterParaRestauranteDto(restaurante));
		});

		return restaurantesDto;
	}
}
