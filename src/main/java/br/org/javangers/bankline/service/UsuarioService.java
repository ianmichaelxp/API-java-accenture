package br.org.javangers.bankline.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.javangers.bankline.controller.dto.UsuarioDTO;
import br.org.javangers.bankline.model.Usuario;
import br.org.javangers.bankline.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	public List<UsuarioDTO> listarUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuarioDTOs = UsuarioDTO.usuarioToDTO(usuarios);
		
		return usuarioDTOs;
	}

	public UsuarioDTO obterUsuarioPorId(Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return new UsuarioDTO(usuario.get());
		}
		return null;
	}

	public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
		String encode = new BCryptPasswordEncoder().encode(usuarioDTO.getSenha());
		usuarioDTO.setSenha(encode);

		usuarioRepository.save(new Usuario(usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getNome(), usuarioDTO.getCpf()));
		return usuarioDTO;
	}

	public UsuarioDTO obterUsuarioPorId(Integer id) {
		// TODO OBTER DE ACORDO COM A CREDENCIAL
		return null;
	}
}


/*
 * {
    "login": "aluno5@email.com",
    "senha": "$2a$10$dty547kETqtuTLkhDUKvq..4Dvr27ZDK3OVZ3carNkrPyZ/xVbNhq",
    "nome": "ian",
    "cpf": "12345678901"
}
 * 
 * {
    "login": "ian50@email.com",
    "senha": "$2a$10$GjlswtOb.fIsGs7Fy44sdekeOZrNHeryyCfvT42eR8fY96TGymow.",
    "nome": "ian",
    "cpf": "12345678901"
}
 * 
 * */
