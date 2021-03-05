package br.org.javangers.bankline.service;

import java.util.List;
import java.util.Optional;

import br.org.javangers.bankline.model.Conta;
import br.org.javangers.bankline.model.PlanoConta;
import br.org.javangers.bankline.model.enums.TipoConta;
import br.org.javangers.bankline.model.enums.TipoMovimento;
import br.org.javangers.bankline.repository.ContaRepository;
import br.org.javangers.bankline.repository.PlanoContaRepository;
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

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private PlanoContaRepository planoContaRepository;

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

		Usuario usuarioSalvo = usuarioRepository.save(new Usuario(usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getNome(), usuarioDTO.getCpf()));

		Conta conta = new Conta(null, 0.0, TipoConta.DEBITO, usuarioSalvo);

		contaRepository.save(conta);

		PlanoConta pc1 = new PlanoConta(null, "RECEITAS", TipoMovimento.RECEITA, true, usuarioSalvo);
		PlanoConta pc2 = new PlanoConta(null, "DESPESAS", TipoMovimento.DESPESA, true, usuarioSalvo);
		PlanoConta pc3 = new PlanoConta(null, "TRANSFERENCIAS", TipoMovimento.TRANSFERENCIA, true, usuarioSalvo);

		planoContaRepository.save(pc1);
		planoContaRepository.save(pc2);
		planoContaRepository.save(pc3);

		UsuarioDTO usuario = new UsuarioDTO(usuarioSalvo);
		return usuario;
	}

	public UsuarioDTO obterUsuarioPorId(Integer id) {
		// TODO OBTER DE ACORDO COM A CREDENCIAL
		return null;
	}
}
