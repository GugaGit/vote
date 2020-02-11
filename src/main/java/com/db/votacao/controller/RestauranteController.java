package com.db.votacao.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.db.votacao.controller.dto.RestauranteDto;
import com.db.votacao.controller.form.RestauranteForm;
import com.db.votacao.converter.RestauranteConverter;
import com.db.votacao.modelo.Restaurante;
import com.db.votacao.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	private static final String PATH_CADASTRAR = "/restaurantes/{id}";

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private RestauranteConverter restauranteConverter;

	List<RestauranteDto> restaurantesDto = new ArrayList<>();

	@GetMapping
	public ResponseEntity<List<RestauranteDto>> listarRestaurante() {
		return ResponseEntity.ok(restauranteService.listarRestaurantes());
	}

	@PostMapping
	public ResponseEntity<RestauranteDto> cadastrar(@RequestBody @Valid RestauranteForm form,
			UriComponentsBuilder uriBuilder) {

		Restaurante restaurante = restauranteService
				.cadastrarRestaurante(restauranteConverter.converterParaRestauranteDto(form.converter()));
		URI uri = uriBuilder.path(PATH_CADASTRAR).buildAndExpand(restaurante.getId()).toUri();
		return ResponseEntity.created(uri).body(restauranteConverter.converterParaRestauranteDto(restaurante));
	}
}
