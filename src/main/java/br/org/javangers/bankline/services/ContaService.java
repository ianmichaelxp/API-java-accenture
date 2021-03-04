package br.org.javangers.bankline.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.javangers.bankline.exception.ObjectNotFoundException;
import br.org.javangers.bankline.model.Conta;
import br.org.javangers.bankline.repository.ContaRepository;

public class ContaService {

	@Autowired
	private ContaRepository contaRepo;
	
	public Conta find(Long id) {
		Optional<Conta> obj = contaRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName()));
	}
	
}
