package br.org.javangers.bankline.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.javangers.bankline.controller.dto.LancamentoDTO;
import br.org.javangers.bankline.controller.dto.UsuarioDTO;
import br.org.javangers.bankline.model.Conta;
import br.org.javangers.bankline.model.Lancamento;
import br.org.javangers.bankline.model.PlanoConta;
import br.org.javangers.bankline.model.Usuario;
import br.org.javangers.bankline.model.enums.TipoMovimento;
import br.org.javangers.bankline.repository.ContaRepository;
import br.org.javangers.bankline.repository.LancamentoRepository;
import br.org.javangers.bankline.repository.PlanoContaRepository;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private PlanoContaRepository planoContaRepository;
	
		public List<Lancamento> listarLancamentos() {
		return lancamentoRepository.findAll();
	}
		
	/*
	public List<LancamentoDTO> listarLancamentos() {
		List<Lancamento> lancamentos = lancamentoRepository.findAll();
		List<LancamentoDTO> lancamentoDTOs = LancamentoDTO.lancamentoToDTO(lancamentos);
		
		return lancamentoDTOs;
	}*/


	public Optional<Lancamento> obterLancamentoPorId(long id) {
		return lancamentoRepository.findById(id);
	}
	
	/*public UsuarioDTO obterLancamentoPorId(Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return new UsuarioDTO(usuario.get());
		}
		return null;
	}*/
	
	public Optional<Lancamento> ExtratoPorPeriodo(LocalDate primeira, LocalDate ultima, long idMinhaConta) {

		
		if (primeira == null || ultima == null || ultima.isBefore(primeira)) {
			throw new IllegalArgumentException();
		}

		return lancamentoRepository.findAllByDataBetweenAndContaDestino(primeira, ultima, idMinhaConta );
	}
	
	public void novoLancamento(LancamentoDTO lancamentoDto) {
		
		
		
		if (lancamentoDto.getIdMinhaConta() == null || lancamentoDto.getValor() == null || lancamentoDto.getValor() < 0
			|| lancamentoDto.getDescricao() == null || lancamentoDto.getTipo() == null
			|| lancamentoDto.getCategoria() == null) {
			
			throw new IllegalArgumentException();
			
		}
		
		
		if ((lancamentoDto.getTipo() == TipoMovimento.TRANSFERENCIA) && (lancamentoDto.getContaDestino() == null)) {
			
			throw new IllegalArgumentException();
			
		}
		

Optional<PlanoConta> planoConta = planoContaRepository.findById(lancamentoDto.getCategoria());
		
		if (!planoConta.isPresent()) {
			throw new IllegalArgumentException();
		}

		Optional<Conta> conta = contaRepository.findById(lancamentoDto.getIdMinhaConta());
		
		if (!conta.isPresent()) {
			throw new IllegalArgumentException();
		}
		
		Conta minhaConta = conta.get();
		
		Lancamento novoLancamento = new Lancamento(minhaConta.getNumero(), LocalDate.now(), lancamentoDto.getDescricao(), lancamentoDto.getTipo(), planoConta.get());
		
		if (lancamentoDto.getTipo() == TipoMovimento.RECEITA) {
			
			novoLancamento.setValor(lancamentoDto.getValor());
			minhaConta.setSaldo(minhaConta.getSaldo() + lancamentoDto.getValor());
			contaRepository.save(minhaConta);
			lancamentoRepository.save(novoLancamento);
			
		}
		
		if (lancamentoDto.getTipo() == TipoMovimento.DESPESA) {
			if (minhaConta.getSaldo() - lancamentoDto.getValor() >= 0) {
				novoLancamento.setValor(-lancamentoDto.getValor());
				minhaConta.setSaldo(minhaConta.getSaldo() - lancamentoDto.getValor());
				contaRepository.save(minhaConta);
				lancamentoRepository.save(novoLancamento);
			}
		}
		
		if (lancamentoDto.getTipo() == TipoMovimento.TRANSFERENCIA)  {

			Optional<Conta> contaDestino = contaRepository.findByContaDestino(lancamentoDto.getContaDestino());
			if (!contaDestino.isPresent()) {
				throw new IllegalArgumentException();
			}

			Conta contaDst = contaDestino.get();
			novoLancamento.setContaDestino(contaDst.getNumero());

			if (minhaConta.getSaldo() - lancamentoDto.getValor() >= 0) {

				minhaConta.setSaldo(minhaConta.getSaldo() - lancamentoDto.getValor());
				contaDst.setSaldo(contaDst.getSaldo() + lancamentoDto.getValor());

				contaRepository.save(minhaConta);
				contaRepository.save(contaDst);


				novoLancamento.setValor(-lancamentoDto.getValor());
				lancamentoRepository.save(novoLancamento);

				Lancamento lancamentoDst = new Lancamento(contaDst.getNumero(), LocalDate.now(), lancamentoDto.getDescricao(), lancamentoDto.getTipo(), planoConta.get());
				lancamentoDst.setValor(lancamentoDto.getValor());
				lancamentoRepository.save(lancamentoDst);

			}

		}
		
	}
	
	
}
