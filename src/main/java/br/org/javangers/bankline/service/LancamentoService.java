package br.org.javangers.bankline.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.javangers.bankline.controller.dto.LancamentoDTO;
import br.org.javangers.bankline.exception.SaldoInsuficienteException;
import br.org.javangers.bankline.model.Conta;
import br.org.javangers.bankline.model.Lancamento;
import br.org.javangers.bankline.model.PlanoConta;
import br.org.javangers.bankline.model.enums.TipoMovimento;
import br.org.javangers.bankline.repository.ContaRepository;
import br.org.javangers.bankline.repository.LancamentoRepository;
import br.org.javangers.bankline.repository.PlanoContaRepository;




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
		
	/*public List<LancamentoDTO> listarLancamentos() {
		List<Lancamento> lancamentos = lancamentoRepository.findAll();
		List<LancamentoDTO> lancamentosDTOs = LancamentoDTO.lancamentoToDTO(lancamentos);
		
		return lancamentosDTOs;
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

		return lancamentoRepository.findAllByDataBetweenAndContaId(primeira, ultima, idMinhaConta );
	}
	
	public void novoLancamento(LancamentoDTO lancamentoDto) {
		
		
		
		if (lancamentoDto.getIdMinhaConta() == null || lancamentoDto.getValor() == null || lancamentoDto.getValor() < 0
			|| lancamentoDto.getDescricao() == null //|| lancamentoDto.getTipo() == null
			|| lancamentoDto.getCategoria() == null) {
			
			throw new IllegalArgumentException();
			
		}
		
		
		if (lancamentoDto.getTipo() == TipoMovimento.TRANSFERENCIA_ENTRE_CONTAS&& lancamentoDto.getContaDestino() == null) {
			
			throw new IllegalArgumentException();
			
		}
		

		Optional<PlanoConta> planoConta = planoContaRepository.findById(Long.valueOf(lancamentoDto.getCategoria()));
		
		if (!planoConta.isPresent()) {
			throw new IllegalArgumentException();
		}

		Optional<Conta> conta ;//TODO = contaRepository.findById(lancamentoDto.getIdContaUsuario());
		
		if (!conta.isPresent()) {
			throw new IllegalArgumentException();
		}
		
		Conta minhaConta = conta.get();
		
		Lancamento novoLancamento = new Lancamento(minhaConta.getNumero(), LocalDate.now(), lancamentoDto.getDescricao(), lancamentoDto.getTipo(), planoConta.getCategoria());
		
		if (lancamentoDto.getTipo() == TipoMovimento.RECEITA) {
			
			novoLancamento.setValor(lancamentoDto.getValor());
//			minhaConta.receitas(lancamentoDto.getValor()); // TODO verificar método, Nailson
			contaRepository.save(minhaConta);
			lancamentoRepository.save(novoLancamento);
			
		}
		
		if (lancamentoDto.getTipo() == TipoMovimento.DESPESA) {
			if (minhaConta.getSaldo() - lancamentoDto.getValor() >= 0) {
				novoLancamento.setValor(-lancamentoDto.getValor());
//				minhaConta.despesas(lancamentoDto.getValor()); //TODO verificar método, Nailson
//				contaRepository.save(minhaConta);//TODO
//				contaRepository.save(novoLancamento);//TODO
			} else {
				throw new SaldoInsuficienteException("Saldo Insulficiente para esta operação"); //Tratar exceção
			}
		}
		
		//if (lancamentoDto.getTipo() == TipoMovimento.TRANSFERENCIAS) {	}
		
	}
	
	
}
