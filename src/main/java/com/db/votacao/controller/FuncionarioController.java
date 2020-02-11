package com.db.votacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.votacao.controller.dto.FuncionarioDto;
import com.db.votacao.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	private static final String PATH_ID = "/{id}";

	@Autowired
	private FuncionarioService funcionarioService;

	@DeleteMapping(path = PATH_ID)
	public ResponseEntity excluirFuncionario(@PathVariable Long id) {
		funcionarioService.excluirFuncionario(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	@Cacheable(value = "listaDeFuncionarios")
	public ResponseEntity<List<FuncionarioDto>> listarFuncionario(@RequestParam(required = false) String nome) {
		if (nome == null) {
			List<FuncionarioDto> funcionariosDto = funcionarioService.listarFuncionariosDto();

			return ResponseEntity.ok(funcionariosDto);
		}

		return ResponseEntity.ok(funcionarioService.listarFuncionarioNome(nome));
	}
}
