package com.db.votacao.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.votacao.controller.dto.VotoDto;
import com.db.votacao.modelo.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

	List<Voto> findByData(LocalDate data);

	List<VotoDto> findByFuncionario(VotoDto votoDto);
}
