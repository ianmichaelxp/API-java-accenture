package br.org.javangers.bankline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.javangers.bankline.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	
}
