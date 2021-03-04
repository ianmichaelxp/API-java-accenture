package br.org.javangers.bankline.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import br.org.javangers.bankline.exception.DataIntegrityException;
import br.org.javangers.bankline.exception.ObjectNotFoundException;
import br.org.javangers.bankline.model.Conta;
import br.org.javangers.bankline.repository.ContaRepository;

public class ContaService {

	@Autowired
	private ContaRepository repo;
	
	public Conta find(Long id) {
		Optional<Conta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName()));
	}
	
	public Conta insert(Conta obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public void delete(Long id) {
		find(id);
		try {
		repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Conta que possui Planos de conta");
		}
	}
	
	public List<Conta> findAll(){
		return repo.findAll();
	}
}
