package br.org.javangers.bankline.model.enums;

public enum TipoMovimento {
	RECEITA(1, "R"),
	DESPESA(2, "D"),
	TRANSFERENCIA(3, "T");
	
	private int cod;
	private String descricao;
	
	private TipoMovimento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoMovimento toEnum(Integer cod) {
		if(cod==null)
			return null;
		
		for(TipoMovimento x: TipoMovimento.values()) {
			if(cod.equals(x.cod))
				return x;
		}
		
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
	
	public static TipoMovimento toEnum(String descricao) {
		if(descricao==null)
			return null;
		
		for(TipoMovimento x: TipoMovimento.values()) {
			if(descricao.equals(x.descricao))
				return x;
		}
		
		throw new IllegalArgumentException("Tipo inválido: "+descricao);
	}
	
}
