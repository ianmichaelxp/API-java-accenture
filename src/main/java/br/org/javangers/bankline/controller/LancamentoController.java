package br.org.javangers.bankline.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.javangers.bankline.controller.dto.LancamentoDTO;
import br.org.javangers.bankline.controller.dto.PlanoContaDTO;
import br.org.javangers.bankline.model.Lancamento;
import br.org.javangers.bankline.model.PlanoConta;
import br.org.javangers.bankline.service.LancamentoService;
import br.org.javangers.bankline.service.PlanoContaService;

@RestController
@RequestMapping("/api/usuarios/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private PlanoContaService planoContaService;
	
	@GetMapping
	public List<Lancamento> getLancamentos() {
		return lancamentoService.listarLancamentos();
	}
	
	@PostMapping
	public void realizarLancamento(@RequestBody LancamentoDTO lancamentoDto) {
		
		 lancamentoService.novoLancamento(lancamentoDto);
	}
	
	@RequestMapping(name = "/planos-conta",method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PlanoContaDTO objDto){
		PlanoConta obj = planoContaService.fromDTO(objDto);
		planoContaService.insert(obj);
		
		return ResponseEntity.ok(null);
	}
	@RequestMapping(name = "/planos-conta",method = RequestMethod.GET)
	public ResponseEntity<List<PlanoContaDTO>> findByUser(@RequestParam(value = "login", defaultValue = "" )String login){
		List<PlanoConta> objs = planoContaService.findByLogin(login);
		List<PlanoContaDTO> objsDto = objs.stream().map(p->new PlanoContaDTO(p)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(objsDto);
	}
	
	
	
	
}
