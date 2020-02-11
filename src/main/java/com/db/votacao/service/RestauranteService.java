package com.db.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.votacao.controller.dto.RestauranteDto;
import com.db.votacao.converter.RestauranteConverter;
import com.db.votacao.exception.RestauranteException;
import com.db.votacao.modelo.Restaurante;
import com.db.votacao.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteConverter restauranteConverter;

	@Autowired
	private RestauranteRepository restauranteRepository;

	private List<RestauranteDto> restaurantes;

	public List<RestauranteDto> listarRestaurantes() {
		if (restauranteRepository.findAll().isEmpty()) {
			throw new RestauranteException();
		}
		
		return restaurantes = converterListaparaListaDto();
	}

	private List<RestauranteDto> converterListaparaListaDto() {
		return restauranteConverter.converterParaListaRestauranteDto(restauranteRepository.findAll());		
	}
	
	public RestauranteDto converterParaDto(Restaurante restaurnte) {
		return restauranteConverter.converterParaRestauranteDto(restaurnte);		
	}

	public Restaurante cadastrarRestaurante(RestauranteDto restauranteDto) {
		return restauranteRepository.save(restauranteConverter.converterParaRestaurante(restauranteDto));
	}	
}
