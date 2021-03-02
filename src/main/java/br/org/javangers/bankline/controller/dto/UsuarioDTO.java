package br.org.javangers.bankline.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

	private String login;
	
	private String senha;
	
	private String nome;
	
	private String cpf;
	
}