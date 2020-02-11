package com.db.votacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.votacao.controller.dto.FuncionarioDto;
import com.db.votacao.controller.dto.RestauranteDto;
import com.db.votacao.controller.dto.VotoDto;
import com.db.votacao.converter.FuncionarioConverter;
import com.db.votacao.converter.RestauranteConverter;
import com.db.votacao.converter.VotoConverter;
import com.db.votacao.modelo.Funcionario;
import com.db.votacao.modelo.Restaurante;
import com.db.votacao.modelo.Voto;
import com.db.votacao.repository.VotoRepository;
import com.db.votacao.service.VotoService;
import com.db.votacao.validator.VotoValidator;

@RunWith(SpringRunner.class)
public class VotoServiceTest {

	private static final String SILVA = "Silva";

	private static final String MAZA = "Maza";

	private static final String PETISKEIRA = "Petiskeira";

	private static final Integer VOTOS_ESPERADOS_3 = 3;

	private static final Integer VOTOS_ESPERADOS_1 = 1;

	private static final Integer VOTOS_ESPERADOS_0 = 0;

	private static final String RESTAURANTE_ELEITO = MAZA;

	@InjectMocks
	private VotoService votoService;

	@Mock
	private VotoDto votoDto;

	@Mock
	private VotoRepository votoRepository;

	@Mock
	private VotoValidator votoValidator;

	@Mock
	private VotoConverter votoConverter;

	@Mock
	private FuncionarioConverter funcionarioConverter;

	@Spy
	private RestauranteConverter restauranteConverter;

	@Test
	public void deveConterTresVotosAoVotar() {
		Mockito.doNothing().when(votoValidator).validaFuncionarioVotouHoje(votoDto);
		Mockito.doNothing().when(votoValidator).validaRestauranteElegivel(votoDto);
		Mockito.doNothing().when(votoValidator).validaRestauranteElegivel(votoDto);

		VotoDto votoDto = votoDto();

		votoService.votar(votoDto);

		assertEquals(VOTOS_ESPERADOS_3, votoDto.getRestauranteDto().getVoto());
	}

	@Test
	public void deveConterUmVotoAoVotar() {
		Mockito.doNothing().when(votoValidator).validaFuncionarioVotouHoje(votoDto);
		Mockito.doNothing().when(votoValidator).validaRestauranteElegivel(votoDto);
		Mockito.doNothing().when(votoValidator).validaRestauranteElegivel(votoDto);

		VotoDto votoDto = votoDto();
		votoDto.getRestauranteDto().setVoto(null);

		votoService.votar(votoDto);

		assertEquals(VOTOS_ESPERADOS_1, votoDto.getRestauranteDto().getVoto());
	}

	@Test
	public void deveElegerRestauranteMaisVotado() {
		List<RestauranteDto> listaDeRestaurantesDtoVotadosHoje = listaDeRestaurantesDtoVotadosHoje();
		List<Voto> listaDtoElegiveisHoje = listaDeVotos();

		VotoService spyVotoService = Mockito.spy(new VotoService());

		Mockito.doReturn(listaDeRestaurantesDtoVotadosHoje).when(spyVotoService).listaDeRestaurantesDtoVotadosHoje();
		Mockito.doReturn(listaDtoElegiveisHoje).when(votoRepository).findByData(dataDeHoje());

		RestauranteDto restauranteEleito = votoService.elege();

		assertEquals(RESTAURANTE_ELEITO, restauranteEleito.getNome());
	}

	@Test
	public void deveLimparVotos() {
		VotoService spyVotoService = Mockito.spy(new VotoService());

		List<Voto> listaDeVotos = listaDeVotos();
		List<VotoDto> listaDeVotosDto = listaDeVotosDto();

		Mockito.doReturn(listaDeVotosDto).when(spyVotoService).listaVotosDto();
		Mockito.doReturn(Boolean.TRUE).when(spyVotoService).isInicioSemana();
		Mockito.doReturn(votoRepository).when(spyVotoService).getVotoRepository();
		Mockito.doReturn(votoRepository).when(spyVotoService).getVotoRepository();
		Mockito.doReturn(listaDeVotos).when(votoRepository).findAll();

		spyVotoService.limparVotos();

		assertEquals(VOTOS_ESPERADOS_0, listaDeVotosDto.get(0).getVoto());
		assertEquals(VOTOS_ESPERADOS_0, listaDeVotosDto.get(1).getVoto());
	}

	private List<VotoDto> listaDeVotosDto() {

		List<VotoDto> listaVotosDto = new ArrayList<>();
		listaVotosDto.add(votoDtoUm());
		listaVotosDto.add(votoDtoDois());

		return listaVotosDto;
	}

	private List<RestauranteDto> listaDeRestaurantesDtoVotadosHoje() {
		List<RestauranteDto> lista = new ArrayList<>();
		lista.add(restauranteDtoMaza());
		lista.add(restauranteDtoMaza());

		return lista;
	}

	private LocalDate dataDeHoje() {
		LocalDate hoje = LocalDate.now();
		return hoje;
	}

	private List<Voto> listaDeVotos() {
		List<Voto> voto = new ArrayList<>();
		voto.add(votoUm());
		voto.add(votoDois());
		voto.add(votoTres());

		return voto;
	}

	private VotoDto votoDto() {
		VotoDto votoDto = VotoDto.builder().id(1L).data(dataDeHoje()).voto(2).funcionarioDto(funcionarioDto())
				.restauranteDto(restauranteDto()).build();
		return votoDto;
	}

	private VotoDto votoDtoUm() {
		VotoDto votoDtoUm = VotoDto.builder().id(1L).data(dataDeHoje()).funcionarioDto(funcionarioDto())
				.restauranteDto(restauranteDtoMaza()).voto(restauranteDtoMaza().getVoto()).build();
		return votoDtoUm;
	}

	private VotoDto votoDtoDois() {
		VotoDto votoDtoDois = VotoDto.builder().id(2L).data(dataDeHoje()).funcionarioDto(funcionarioDto())
				.restauranteDto(restauranteDtoSilva()).voto(restauranteDtoSilva().getVoto()).build();
		return votoDtoDois;
	}

	private RestauranteDto restauranteDto() {
		RestauranteDto restauranteDto = RestauranteDto.builder().id(1L).voto(2).elegivel(Boolean.TRUE).dataEleito(null)
				.nome(PETISKEIRA).build();
		return restauranteDto;
	}

	private FuncionarioDto funcionarioDto() {
		FuncionarioDto funcionarioDto = FuncionarioDto.builder().id(1L).build();
		return funcionarioDto;
	}

	private Voto votoUm() {
		Voto votoUm = Voto.builder().id(1L).data(dataDeHoje()).funcionario(funcionarioUm())
				.restaurante(restauranteSilva()).build();
		return votoUm;
	}

	private Voto votoDois() {
		Voto votoUm = Voto.builder().id(2L).data(dataDeHoje()).funcionario(funcionarioDois())
				.restaurante(restauranteSilva()).build();
		return votoUm;
	}

	private Voto votoTres() {
		Voto votoUm = Voto.builder().id(3L).data(dataDeHoje()).funcionario(funcionarioTres())
				.restaurante(restauranteMaza()).build();
		return votoUm;
	}

	private RestauranteDto restauranteDtoMaza() {
		RestauranteDto restaurante = RestauranteDto.builder().id(1L).nome(MAZA).voto(3).build();
		return restaurante;
	}

	private RestauranteDto restauranteDtoSilva() {
		RestauranteDto restaurante = RestauranteDto.builder().id(1L).nome(MAZA).voto(3).build();
		return restaurante;
	}

	private Restaurante restauranteSilva() {
		Restaurante restaurante = Restaurante.builder().id(1L).nome(SILVA).voto(1).build();
		return restaurante;
	}

	private Restaurante restauranteMaza() {
		Restaurante restaurante = Restaurante.builder().id(1L).nome(MAZA).voto(3).build();
		return restaurante;
	}

	private Funcionario funcionarioUm() {
		Funcionario funcionario = Funcionario.builder().id(1L).build();
		return funcionario;
	}

	private Funcionario funcionarioDois() {
		Funcionario funcionario = Funcionario.builder().id(2L).build();
		return funcionario;
	}

	private Funcionario funcionarioTres() {
		Funcionario funcionario = Funcionario.builder().id(3L).build();
		return funcionario;
	}

}
