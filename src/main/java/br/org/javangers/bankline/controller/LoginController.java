package br.org.javangers.bankline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.javangers.bankline.config.security.TokenService;
import br.org.javangers.bankline.controller.dto.LoginDTO;
import br.org.javangers.bankline.controller.dto.TokenDTO;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Validated LoginDTO loginDTO ){
		
		UsernamePasswordAuthenticationToken dadosLogin = loginDTO.converter();
		try {
			Authentication authenticate = authenticationManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authenticate);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
						
		} catch (AuthenticationException e) {
			
			return ResponseEntity.badRequest().build();
		}
		
	}

}
