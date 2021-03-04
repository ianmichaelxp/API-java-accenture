package br.org.javangers.bankline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.org.javangers.bankline.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	@Transactional(readOnly = true)
	Conta findByNumeroAndTipo(String numero, String tipo);
	
	@Transactional(readOnly = true)
	List<Conta> findByClienteId(Long clienteId);
	
	@Transactional(readOnly = true)
	List<Conta> findByNumero(String numero);
}
