package br.org.javangers.bankline.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.javangers.bankline.controller.dto.UsuarioDTO;
import br.org.javangers.bankline.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired 
	private UsuarioService usuarioService;

	@GetMapping
	public List<UsuarioDTO> obterListaUsuarios() {
		List<UsuarioDTO> usuariosDTO = usuarioService.listarUsuarios();
		return usuariosDTO;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> obterUsuario(@PathVariable(value="id") Integer id) {
		
		UsuarioDTO usuarioDTOs = usuarioService.obterUsuarioPorId(id);
		if (usuarioDTOs != null) {
			return ResponseEntity.ok().body(usuarioDTOs);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
		
	@PostMapping
	public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException{
		UsuarioDTO user = usuarioService.salvarUsuario(usuarioDTO);

		return ResponseEntity.created(new URI("/usuarios")).body(user);
	}
		
}
