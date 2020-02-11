package com.db.votacao.controller;

import java.net.URI;

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
import com.db.votacao.controller.dto.VotoDto;
import com.db.votacao.converter.VotoConverter;
import com.db.votacao.modelo.Voto;
import com.db.votacao.service.VotoService;

@RestController
@RequestMapping("/voto")
public class VotoController {

	private static final String PATH_VOTAR = "/voto/{id}";

	private VotoConverter votoConverter;

	private VotoService votoService;

	@Autowired
	public VotoController(VotoService votacaoService, VotoConverter votoConverter) {
		this.votoService = votacaoService;
		this.votoConverter = votoConverter;
	}

	@PostMapping
	public ResponseEntity<VotoDto> votar(@RequestBody @Valid VotoDto votoDto, UriComponentsBuilder uriBuilder) {
		Voto voto = votoService.votar(votoDto);
		URI uri = uriBuilder.path(PATH_VOTAR).buildAndExpand(voto.getId()).toUri();
		return ResponseEntity.created(uri).body(votoConverter.converterParaVotoDto(voto));
	}

	@GetMapping
	public ResponseEntity<RestauranteDto> resultadoVotacao() {
		RestauranteDto restauranteDto = votoService.elege();
		return ResponseEntity.ok(restauranteDto);
	}
}
