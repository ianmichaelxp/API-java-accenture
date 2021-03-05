package br.org.javangers.bankline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.org.javangers.bankline.model.PlanoConta;

public interface PlanoContaRepository extends JpaRepository<PlanoConta, Long>{

	@Transactional(readOnly = true)
	List<PlanoConta> findByLogin(String login);
	
	@Transactional(readOnly = true)
	List<PlanoConta> findByUsuarioId(Long usuarioId);
}
