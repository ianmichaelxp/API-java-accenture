package br.org.javangers.bankline.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import br.org.javangers.bankline.exception.DataIntegrityException;
import br.org.javangers.bankline.exception.ObjectNotFoundException;
import br.org.javangers.bankline.model.PlanoConta;
import br.org.javangers.bankline.repository.PlanoContaReposirory;

public class PlanoContaService {

	@Autowired
	private PlanoContaReposirory repo;
	
	public PlanoConta find(Long id) {
		Optional<PlanoConta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + PlanoConta.class.getName()));
	}
	
	public PlanoConta insert(PlanoConta obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public void delete(Long id) {
		find(id);
		try {
		repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma PlanoConta que possui Planos de conta");
		}
	}
	
	public List<PlanoConta> findAll(){
		return repo.findAll();
	}
}
