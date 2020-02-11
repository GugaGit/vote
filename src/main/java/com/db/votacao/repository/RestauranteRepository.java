package com.db.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.votacao.modelo.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	List<Restaurante> findByNome(String nomeRestaurante);

}
