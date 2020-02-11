package com.db.votacao.validator;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.votacao.controller.dto.FuncionarioDto;
import com.db.votacao.controller.dto.RestauranteDto;
import com.db.votacao.controller.dto.VotoDto;
import com.db.votacao.converter.FuncionarioConverter;
import com.db.votacao.converter.RestauranteConverter;
import com.db.votacao.exception.VotacaoException;
import com.db.votacao.repository.VotoRepository;
import com.db.votacao.validator.VotoValidator;

@RunWith(SpringRunner.class)
public class VotoValidatorTest {

	private static final String MSG_ESPERADA_VOTO = "Seu voto já foi computado hoje.";
	private static final String MSG_ESPERADA_RESTAURANTE = "Esse restaurante já foi eleito essa semana, por favor escolha outro.";
	
	@InjectMocks
	private VotoValidator votoValidator;
	
	@Mock
	private FuncionarioConverter funcionarioConverter;
	
	@Mock
	private RestauranteConverter restauranteConverter;
	
	@Mock
	private VotoRepository votoRepository;
	
	@Spy
	private VotoDto votoDto;
	
	@Spy
	private FuncionarioDto funcionarioDto;
	
	@Spy
	private RestauranteDto restauranteDto;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private LocalDate dataDeHoje() {
		LocalDate hoje = LocalDate.now();
		return hoje;
	}

	@Test(expected = VotacaoException.class)
	public void deveDarErroFuncionarioVotouHoje() {
		funcionarioDto = FuncionarioDto.builder().id(1l).dataVoto(dataDeHoje()).build();
		votoDto = VotoDto.builder().id(1L).data(dataDeHoje()).funcionarioDto(funcionarioDto).build();

		votoValidator.validaFuncionarioVotouHoje(votoDto);

		exception.expectMessage(MSG_ESPERADA_VOTO);
	}

	@Test(expected = VotacaoException.class)
	public void deveDarErroRestauranteEleitoNestaSemana() {
		restauranteDto = RestauranteDto.builder().id(1l).elegivel(Boolean.FALSE).build();
		votoDto = VotoDto.builder().id(1L).data(dataDeHoje()).restauranteDto(restauranteDto).build();

		votoValidator.validaRestauranteElegivel(votoDto);

		exception.expectMessage(MSG_ESPERADA_RESTAURANTE);
	}
}
