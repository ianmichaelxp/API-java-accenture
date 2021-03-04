package br.org.javangers.bankline.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.javangers.bankline.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	Optional<Lancamento> findAllByDataBetweenAndContaId(LocalDate primeira, LocalDate ultima, long idMinhaConta);
}
