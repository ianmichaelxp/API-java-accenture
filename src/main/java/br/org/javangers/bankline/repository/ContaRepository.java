package br.org.javangers.bankline.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.org.javangers.bankline.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {


	@Transactional(readOnly = true)
	List<Conta> findByUsuarioId(Long clienteId);

	Optional<Conta> findByUsuario_Login(String contaDestino);

	@Transactional(readOnly = true)
	List<Conta> findByNumero(String numero);

	Optional<Conta> findByNumeroAndTipo(String numero, String tipo);

	List<Conta> findByUsuarioId(Integer usuarioId);
}
