package br.org.javangers.bankline.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.javangers.bankline.model.Conta;
import br.org.javangers.bankline.repository.ContaReposirory;

public class ContaService {

	@Autowired
	private ContaReposirory contaRepo;
	
//	public Conta find(Long id) {
//		Optional<Conta> obj = contaRepo.findById(id);
//		return obj.orElseThrow(() -> new ObjectNotFoundException(
//				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
//	}
}
