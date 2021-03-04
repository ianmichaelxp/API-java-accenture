package br.org.javangers.bankline.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.org.javangers.bankline.controller.dto.UsuarioDTO;
import br.org.javangers.bankline.model.Usuario;
import br.org.javangers.bankline.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired 
	private UsuarioService usuarioService;
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Usuario criarUsuario(@Validated @RequestBody Usuario usuario) {
//		
//		return usuarioService.salvarUsuario(usuario);
//	}

	@GetMapping
	public List<UsuarioDTO> obterListaUsuarios() {
		List<UsuarioDTO> usuariosDTO = usuarioService.listarUsuarios();
		return usuariosDTO;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> obterUsuario(@PathVariable(value="id") Long id) {
		
		UsuarioDTO usuarioDTOs = usuarioService.obterUsuarioPorId(id);
		if (usuarioDTOs != null) {
			return ResponseEntity.ok().body(usuarioDTOs);
		} else {
			//ref
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) 
	 * Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	 * @param usuarioDTO
	 * @param uriBuilder
	 * 
	 * 
	 * 
	 * @return
	 *@PostMapping
    public ResponseEntity<EquipamentoDTO> criarEquipamento(@Valid @RequestBody EquipamentoDTO equipamentoDTO) throws URISyntaxException
    {
        EquipamentoDTO equipamentoSalvo = equipamentoServico.salvarEquipamento(equipamentoDTO);
        return ResponseEntity.created(new URI("/api/equipamentos")).body(equipamentoSalvo);
    };
	 *
	 */
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException{
		UsuarioDTO user = usuarioService.salvarUsuario(usuarioDTO);
		System.out.println(user);
		return ResponseEntity.created(new URI("/usuarios")).body(user);
	}
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public UsuarioDTO createUser(@Validated @RequestBody UsuarioDTO usuario) {
//		System.out.println(usuario);
//		return usuario;
//	}
	
	
	
	
	
}
