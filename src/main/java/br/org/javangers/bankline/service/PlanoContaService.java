package br.org.javangers.bankline.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import br.org.javangers.bankline.controller.dto.PlanoContaDTO;
import br.org.javangers.bankline.exception.DataIntegrityException;
import br.org.javangers.bankline.exception.ObjectNotFoundException;
import br.org.javangers.bankline.model.PlanoConta;
import br.org.javangers.bankline.model.Usuario;
import br.org.javangers.bankline.repository.PlanoContaRepository;
import br.org.javangers.bankline.repository.UsuarioRepository;

public class PlanoContaService {

	@Autowired
	private PlanoContaRepository repo;
	
	@Autowired 
	private UsuarioRepository repoUser;
	
	
	public PlanoConta find(Long id) {
		Optional<PlanoConta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + PlanoConta.class.getName()));
	}
	
	public PlanoConta insert(PlanoConta obj) {
		String login = obj.getLogin();
		obj.setId(null);
		Optional<Usuario> user = repoUser.findByLogin(obj.getLogin());
		user.orElseThrow(() -> new ObjectNotFoundException(
				 "Usuario não encontrado! Login: " + login));
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

	public List<PlanoConta> findByLogin(String login){
		List<PlanoConta> list = repo.findByLogin(login);
		
		return list;
	}
	
	
	public PlanoConta fromDTO(@Valid PlanoContaDTO objDto) {
		PlanoConta obj = new PlanoConta(objDto);
		return obj;
	}
	
	
}
