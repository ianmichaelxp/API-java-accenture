package br.org.javangers.bankline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(nullable = false, unique = true, length = 20)
	private String login;
	
	@NonNull
	private String senha;
	
	@NonNull
	@Column(nullable = false)
	private String nome;
	
	@NonNull
	@Column(nullable = false, length = 11)
	private String cpf;
	
}
