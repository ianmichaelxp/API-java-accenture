package br.org.javangers.bankline.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Usuario salvarUsuario(Usuario usuario) {
		// TODO A SER IMPLEMENTADO QUANDO QUANDO LOGIN ESTIVER PRONTO
		return null;
	}
}
