package br.org.javangers.bankline.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

	private String login;
	private String senha;
	
	public UsernamePasswordAuthenticationToken converter() {
		UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(getLogin(), getSenha());
		return userAuth;
	}
}
