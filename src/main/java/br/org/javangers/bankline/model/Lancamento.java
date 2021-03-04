package br.org.javangers.bankline.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.org.javangers.bankline.model.enums.TipoMovimento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 20)
	private String minhaConta;
	
	@Column(nullable = false, length = 10)
	private LocalDate data;
	
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false, length = 100)
	private String descricao;
	
	@Column(nullable = true, length = 20)
	private String contaDestino;

	
	@Column(nullable = false)
	private TipoMovimento tipo;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria", referencedColumnName = "id")
	private PlanoConta categoria;
	
	public Lancamento(String minhaConta, LocalDate data, String descricao, TipoMovimento tipo, PlanoConta categoria) {
		super();
		this.minhaConta = minhaConta;
		this.data = data;
		this.descricao = descricao;
		this.tipo = tipo;
		this.categoria = categoria;
	}		
	
}
