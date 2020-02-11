package com.db.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.votacao.controller.dto.FuncionarioDto;
import com.db.votacao.converter.FuncionarioConverter;
import com.db.votacao.exception.FuncionarioException;
import com.db.votacao.modelo.Funcionario;
import com.db.votacao.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioConverter funcionarioConverter;
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	private List<Funcionario> funcionarios;

	private List<FuncionarioDto> funcionariosDto;

	public Funcionario cadastroFuncionario(FuncionarioDto funcionarioDTO) {
		return funcionarioRepository.save(funcionarioConverter.converterParaFuncionario(funcionarioDTO));
	}

	public List<FuncionarioDto> listarFuncionarioNome(String nome) {
		funcionarios = funcionarioRepository.findByNome(nome);
		if (funcionarios.isEmpty()) {
			throw new FuncionarioException();
		}
		return funcionariosDto = converterListaParaListDto(funcionarios);
	}

	private List<FuncionarioDto> converterListaParaListDto(List<Funcionario> funcionarios) {

		return funcionarioConverter.converterListaParaListaDto(funcionarios);
	}

	public void excluirFuncionario(Long id) {
		funcionarioRepository.findById(id).orElseThrow(FuncionarioException::new);
		funcionarioRepository.deleteById(id);
	}

	public List<FuncionarioDto> listarFuncionariosDto() {
		List<FuncionarioDto> funcionarios = funcionarioConverter
				.converterListaParaListaDto(funcionarioRepository.findAll());
		return funcionarios;
	}

}
