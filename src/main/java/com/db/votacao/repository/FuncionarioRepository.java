package com.db.votacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.votacao.modelo.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findByNome(String nomeFuncionario);

	Optional<Funcionario> findByEmail(String email);
}
