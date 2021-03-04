package br.org.javangers.bankline.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.org.javangers.bankline.model.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

	//Cannot construct instance of `br.org.javangers.bankline.controller.dto.UsuarioDTO` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
	public UsuarioDTO(Usuario usuario) {
		this.login = usuario.getLogin();
		this.senha = usuario.getSenha();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
	}

	private String login;
	
	private String senha;
	
	private String nome;
	
	private String cpf;
		
	public static List<UsuarioDTO> usuarioToDTO(List<Usuario> usuarios) {
	    List<UsuarioDTO> UsuariosDTOs = usuarios.stream().map(usuario -> {
	        return new UsuarioDTO(usuario);
	    }).collect(Collectors.toList());

	    return UsuariosDTOs;
	}

	public static Usuario usuarioToEntity(UsuarioDTO usuarioDTO) {
		return new Usuario();
	}
	
	
}