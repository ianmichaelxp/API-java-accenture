package br.org.javangers.bankline.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import br.org.javangers.bankline.model.enums.TipoConta;

public class Conta implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String numero;
	private Double saldo;
	private String tipo;
	private Date data;
	
	private Usuario usuario;
	
	public Conta() {
		this.data = Calendar.getInstance().getTime();
	}

	public Conta(Long id, String numero, Double saldo, TipoConta tipo, Usuario usuario) {
		super();
		this.id = id;
		this.numero = numero;
		this.saldo = saldo;
		this.tipo = tipo.getDescricao();
		this.usuario = usuario;
		this.data = Calendar.getInstance().getTime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo.getDescricao();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
