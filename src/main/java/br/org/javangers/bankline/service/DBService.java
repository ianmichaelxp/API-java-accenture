package br.org.javangers.bankline.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.javangers.bankline.model.Conta;
import br.org.javangers.bankline.model.PlanoConta;
import br.org.javangers.bankline.model.Usuario;
import br.org.javangers.bankline.model.enums.TipoConta;
import br.org.javangers.bankline.model.enums.TipoMovimento;
import br.org.javangers.bankline.repository.ContaRepository;
import br.org.javangers.bankline.repository.PlanoContaRepository;
import br.org.javangers.bankline.repository.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private ContaRepository contaS;
	
	@Autowired
	private PlanoContaRepository planoContaS;
	
	@Autowired
	private UsuarioRepository usuarioS;
	
	
	public void instanciateDatabase() throws ParseException {
		Usuario user1 = new Usuario("nahtx", "123", "Nai Santos", "00000000001");
		
		Conta c1 = new Conta(null, 0.0, TipoConta.CREDITO, user1);
		Conta c2 = new Conta(null, 0.0, TipoConta.DEBITO, user1);
		
		PlanoConta pl1 = new PlanoConta(null,  "Internet", TipoMovimento.DESPESA, true, user1);
		
		usuarioS.save(user1);
		contaS.saveAll(Arrays.asList(c1,c2));
		planoContaS.save(pl1);
		
	}
}
