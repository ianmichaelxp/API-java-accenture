package br.org.javangers.bankline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.javangers.bankline.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	Optional<Conta> findByContaDestino(String contaDestino);

	
}
