package br.org.javangers.bankline.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.springframework.lang.NonNull;

import br.org.javangers.bankline.controller.dto.PlanoContaDTO;
import br.org.javangers.bankline.model.enums.TipoMovimento;

@Entity
public class PlanoConta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String login;
	private String descricao;
	@NonNull
	private String tipoMovimento;
	private Date data;
	private boolean padrao;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public PlanoConta() {
		this.data = Calendar.getInstance().getTime();
	}

	public PlanoConta(Long id, String descricao, TipoMovimento tipoMovimento, boolean padrao, Usuario usuario) {
		super();
		this.id = id;
		this.login = usuario.getUsername();
		this.descricao = descricao;
		this.tipoMovimento = (tipoMovimento == null) ? null : tipoMovimento.getDescricao();
		this.data = Calendar.getInstance().getTime();
		this.padrao = padrao;
		this.usuario = usuario;
	}

	public PlanoConta(PlanoContaDTO o) {
		this.id = o.getId();
		this.login = o.getLogin();
		this.descricao = o.getDescricao();
		this.padrao = o.isPadrao();
		this.tipoMovimento = o.getTipoMovimento();
		this.data = Calendar.getInstance().getTime();		
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isPadrao() {
		return padrao;
	}

	public void setPadrao(boolean padrao) {
		this.padrao = padrao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		setLogin(usuario.getUsername());
		this.usuario = usuario;
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
		PlanoConta other = (PlanoConta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlanoConta [id=");
		builder.append(id);
		builder.append(", login=");
		builder.append(login);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", tipoMovimento=");
		builder.append(tipoMovimento);
		builder.append(", data=");
		builder.append(data);
		builder.append(", padrao=");
		builder.append(padrao);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append("]");
		return builder.toString();
	}

	
	
}
