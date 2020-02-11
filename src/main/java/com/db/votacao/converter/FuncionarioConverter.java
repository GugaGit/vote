package com.db.votacao.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.db.votacao.controller.dto.FuncionarioDto;
import com.db.votacao.modelo.Funcionario;

@Component
public class FuncionarioConverter {

	public FuncionarioDto converterParaFuncionarioDto(Funcionario funcionario) {
		return FuncionarioDto.builder().id(funcionario.getId()).nome(funcionario.getNome())
				.dataVoto(funcionario.getDataVoto()).build();
	}

	public Funcionario converterParaFuncionario(FuncionarioDto funcionarioDto) {
		return Funcionario.builder().id(funcionarioDto.getId()).nome(funcionarioDto.getNome())
				.dataVoto(funcionarioDto.getDataVoto()).build();
	}

	public List<FuncionarioDto> converterListaParaListaDto(List<Funcionario> funcionarios) {
		List<FuncionarioDto> funcionariosDto = new ArrayList<>();
		funcionarios.forEach(funcionario -> {
			funcionariosDto.add(converterParaFuncionarioDto(funcionario));
		});

		return funcionariosDto;
	}
}
