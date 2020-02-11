package com.db.votacao.validator;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.db.votacao.controller.dto.FuncionarioDto;
import com.db.votacao.controller.dto.VotoDto;
import com.db.votacao.exception.VotacaoException;

@Component
public class VotoValidator {

	private static final String MSG_ERRO_VOTO_JA_EFETIVADO_HOJE = "Seu voto já foi computado hoje.";

	private static final String MSG_ERRO_RESTAURANTE_JA_ELEITO_ESSA_SEMANA = "Esse restaurante já foi eleito essa semana, por favor escolha outro.";

	public LocalDate dataDeHoje() {
		LocalDate hoje = LocalDate.now();
		return hoje;
	}

	private Boolean funcionarioVotouHoje(FuncionarioDto funcionarioDto) {
		return funcionarioDto.getDataVoto().equals(dataDeHoje()) ? Boolean.TRUE : Boolean.FALSE;
	}

	public void validaFuncionarioVotouHoje(VotoDto votoDto) {
		if (funcionarioVotouHoje(votoDto.getFuncionarioDto())) {
			throw new VotacaoException(MSG_ERRO_VOTO_JA_EFETIVADO_HOJE);
		}
	}

	public void validaRestauranteElegivel(VotoDto votoDto) {
		if (Boolean.FALSE.equals(votoDto.getRestauranteDto().getElegivel())) {
			throw new VotacaoException(MSG_ERRO_RESTAURANTE_JA_ELEITO_ESSA_SEMANA);
		}
	}
}
