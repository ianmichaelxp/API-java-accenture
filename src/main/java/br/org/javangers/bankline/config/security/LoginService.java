package br.org.javangers.bankline.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.javangers.bankline.model.Usuario;
import br.org.javangers.bankline.repository.UsuarioRepository;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
		if(usuario.isPresent()) {
			System.out.println("está sim");
			return usuario.get();		
		}
		throw new UsernameNotFoundException("Dados inválido!");
	}

}
