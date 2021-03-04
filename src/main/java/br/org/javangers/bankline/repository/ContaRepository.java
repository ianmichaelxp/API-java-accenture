package br.org.javangers.bankline.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.javangers.bankline.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	Optional<Conta> findByUsuario_Login(String contaDestino);

	List<Conta> findByNumero(String numero);

	Optional<Conta> findByNumeroAndTipo(String numero, String tipoConta);

	List<Conta> findByUsuarioId(Integer usuarioId);

	
}
