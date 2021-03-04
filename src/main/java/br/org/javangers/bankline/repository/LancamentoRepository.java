package br.org.javangers.bankline.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.javangers.bankline.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	Optional<Lancamento> findAllByDataBetweenAndContaDestino(LocalDate primeira, LocalDate ultima, long idMinhaConta);

	List<Lancamento> findAllByDataBetween(LocalDate inicio, LocalDate fim);
	List<Lancamento> findTop10ByContaDestinoOrderByDataDesc(String contaId);
	List<Lancamento> findAllByDataBetweenAndContaDestino(LocalDate inicio, LocalDate fim, Long contaId);
}
