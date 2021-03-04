package br.org.javangers.bankline.model.enums;

public enum TipoConta {
	DEBITO(1, "D"),
	CREDITO(2, "C");
	
	private int cod;
	private String descricao;
	
	private TipoConta(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoConta toEnum(Integer cod) {
		if(cod==null)
			return null;
		
		for(TipoConta x: TipoConta.values()) {
			if(cod.equals(x.cod))
				return x;
		}
		
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
	
	public static TipoConta toEnum(String descricao) {
		if(descricao==null)
			return null;
		
		for(TipoConta x: TipoConta.values()) {
			if(descricao.equals(x.descricao))
				return x;
		}
		
		throw new IllegalArgumentException("Tipo inválido: "+descricao);
	}
	
}
