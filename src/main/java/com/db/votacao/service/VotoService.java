package com.db.votacao.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.votacao.controller.dto.RestauranteDto;
import com.db.votacao.controller.dto.VotoDto;
import com.db.votacao.converter.RestauranteConverter;
import com.db.votacao.converter.VotoConverter;
import com.db.votacao.modelo.Restaurante;
import com.db.votacao.modelo.Voto;
import com.db.votacao.repository.VotoRepository;
import com.db.votacao.validator.VotoValidator;

import lombok.Getter;

@Service
@Getter
public class VotoService {

	private static final Integer ZERO_VOTOS = 0;

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private VotoConverter votoConverter;

	@Autowired
	private RestauranteConverter restauranteConverter;

	@Autowired
	private VotoValidator votoValidator;

	public Voto votar(VotoDto votoDto) {
		validaVotocao(votoDto);
		votoRealizado(votoDto);
		adicionaVotoParaRestaurante(votoDto);
		return salvaVoto(votoDto);
	}

	public Voto salvaVoto(VotoDto votoDto) {
		return getVotoRepository().save(votoConverter.converterParaVoto(votoDto));
	}

	private void adicionaVotoParaRestaurante(VotoDto votoDto) {
		if (votoDto.getRestauranteDto().getVoto() == null) {
			votoDto.getRestauranteDto().setVoto(+1);
		} else {
			votoDto.getRestauranteDto().setVoto(votoDto.getRestauranteDto().getVoto() + 1);
		}
	}

	private void votoRealizado(VotoDto votoDto) {
		votoDto.getFuncionarioDto().setDataVoto(votoDto.getData());
	}

	public void validaVotocao(VotoDto votoDto) {
		limparVotos();
		votoValidator.validaFuncionarioVotouHoje(votoDto);
		votoValidator.validaRestauranteElegivel(votoDto);
	}

	private LocalDate dataDeHoje() {
		LocalDate hoje = LocalDate.now();
		return hoje;
	}

	public Boolean isInicioSemana() {
		Boolean domingo = dataDeHoje().equals(dataDeHoje().with(DayOfWeek.SUNDAY)) ? Boolean.TRUE : Boolean.FALSE;
		return domingo;
	}

	public List<Voto> listaElegiveisHoje() {
		LocalDate dataEleicao = dataDeHoje();
		List<Voto> listaDeVotosDeHoje = votoRepository.findByData(dataEleicao);
		return listaDeVotosDeHoje;
	}

	public List<RestauranteDto> listaDeRestaurantesDtoVotadosHoje() {
		List<Restaurante> listaDeVotosDeHoje = listaElegiveisHoje().stream().map(Voto::getRestaurante)
				.collect(Collectors.toList());

		List<RestauranteDto> listaRestaurantesDto = restauranteConverter
				.converterParaListaRestauranteDto(listaDeVotosDeHoje);

		return listaRestaurantesDto;
	}

	public RestauranteDto elege() {

		List<RestauranteDto> restaurantesDto = listaDeRestaurantesDtoVotadosHoje();

		RestauranteDto eleito = restaurantesDto.stream().max(Comparator.comparing(RestauranteDto::getVoto)).get();

		return eleito;
	}

	public void tornarRestauranteNaoElegivelNaSemana(VotoDto votoDto) {
		votoDto.getRestauranteDto().setElegivel(Boolean.FALSE);
	}

	public List<Voto> listaVotos() {
		return getVotoRepository().findAll();
	}

	public List<VotoDto> listaVotosDto() {
		return getVotoConverter().converterListaParaVotoDto(listaVotos());
	}

	public VotoDto zeraVotos(VotoDto votoDto) {
		votoDto.getRestauranteDto().setVoto(ZERO_VOTOS);
		votoDto.setVoto(ZERO_VOTOS);
		return votoDto;
	}

	public VotoDto tornaElegivel(VotoDto votoDto) {
		votoDto.getRestauranteDto().setElegivel(Boolean.TRUE);
		return votoDto;
	}

	public void limparVotos() {
		if (isInicioSemana()) {
			listaVotosDto().forEach(votoDto -> {
				zeraVotos(votoDto);
				tornaElegivel(votoDto);
			});
		}
	}
}
