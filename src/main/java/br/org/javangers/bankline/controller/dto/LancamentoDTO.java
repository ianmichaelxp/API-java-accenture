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
	
	
	
}
