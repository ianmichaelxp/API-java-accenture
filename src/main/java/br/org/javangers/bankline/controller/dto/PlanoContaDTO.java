package br.org.javangers.bankline.controller.dto;

import java.io.Serializable;

import org.springframework.lang.NonNull;

import br.org.javangers.bankline.model.PlanoConta;

public class PlanoContaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Long id;
	@NonNull
	private String login;
	
	private String descricao;
	@NonNull
	private String tipoMovimento;
	@NonNull
	private boolean padrao;

	public PlanoContaDTO() {
		// TODO Auto-generated constructor stub
	}

	public PlanoContaDTO(Long id, String login, String descricao, String tipoMovimento, boolean padrao) {
		super();
		this.id = id;
		this.login = login;
		this.descricao = descricao;
		this.tipoMovimento = tipoMovimento;
		this.padrao = padrao;
	}

	public PlanoContaDTO(PlanoConta p) {
		this.id = p.getId();
		this.login = p.getLogin();
		this.descricao = p.getDescricao();
		this.tipoMovimento = p.getTipoMovimento();
		this.padrao = p.isPadrao();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public boolean isPadrao() {
		return padrao;
	}

	public void setPadrao(boolean padrao) {
		this.padrao = padrao;
	}
	
	
}
