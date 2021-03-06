package br.org.javangers.bankline.config.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.org.javangers.bankline.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${javangers.jwt.expiration}")
	private String expiration;
	
	@Value("${javangers.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authenticate) {
		
		Usuario usuarioLogado = (Usuario) authenticate.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		String compact = "Bearer "+Jwts.builder()
							.setIssuer("BankLine")
							.setSubject(usuarioLogado.getId().toString())
							.setIssuedAt(hoje)
							.setExpiration(dataExpiracao)
							.signWith(SignatureAlgorithm.HS256, secret)
							.compact();
		
		return compact;
		
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long obterIdUsuarioViaToken(String token) {
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		String tokenCompacto = token.substring(7, token.length());
		
		Long id = getIdUsuario(tokenCompacto);
		
		return id;
	}
	
	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	
}
