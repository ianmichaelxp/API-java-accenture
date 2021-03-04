package br.org.javangers.bankline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.javangers.bankline.controller.dto.LancamentoDTO;
import br.org.javangers.bankline.model.Lancamento;
import br.org.javangers.bankline.service.LancamentoService;

@RestController
@RequestMapping("/usuarios/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
//	@GetMapping
//	public List<Lancamento> getLancamentos() {
//		List<LancamentoDTO> lancamentosDTO = lancamentoService.listarLancamentos();
//		return lancamentosDTO;
//	}
}
