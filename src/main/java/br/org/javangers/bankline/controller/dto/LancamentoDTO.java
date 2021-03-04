package br.org.javangers.bankline.controller.dto;

import br.org.javangers.bankline.model.enums.TipoMovimento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter

public class LancamentoDTO {
	
	private Long idMinhaConta;
	private Double valor;
	private String descricao;
	private TipoMovimento tipo;
	private Long categoria;
	private String ContaDestino;
	

	/*public LancamentoDTO(Lancamento lancamento) {
		super();
		this.idMinhaConta = lancamento.getidMinhaConta;
		this.valor = lancamento.getValor();
		this.descricao = lancamento.getDescricao();
		this.categoria = lancamento.getCategoria();
		this.numeroContaDestino = lancamento.getContaDestino();
	}
	
	
	public static List<LancamentoDTO> lancamentoToDTO(List<Lancamento> lancamentos) {
	   List<LancamentoDTO> LancamentosDTOs = lancamentos.stream().map(lancamento -> {
	       return new LancamentoDTO(lancamento);
	    }).collect(Collectors.toList());

	    return LancamentosDTOs;
	}*/
	
	
	
}
