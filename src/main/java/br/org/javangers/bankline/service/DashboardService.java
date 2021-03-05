package br.org.javangers.bankline.service;

import java.util.List;
import java.util.Optional;

import br.org.javangers.bankline.controller.dto.ContaDTO;
import br.org.javangers.bankline.controller.dto.DashboardDTO;
import br.org.javangers.bankline.controller.dto.ExtratoDTO;
import br.org.javangers.bankline.controller.dto.ExtratoRequestDTO;
import br.org.javangers.bankline.model.Conta;
import br.org.javangers.bankline.model.Lancamento;
import br.org.javangers.bankline.model.Usuario;
import br.org.javangers.bankline.model.enums.TipoConta;
import br.org.javangers.bankline.repository.ContaRepository;
import br.org.javangers.bankline.repository.LancamentoRepository;
import br.org.javangers.bankline.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public DashboardDTO getUsuarioDash(String login) {

        DashboardDTO dashboard = new DashboardDTO();

        Optional<Usuario> usuarioBuscado = usuarioRepository.findByLogin(login);

        System.out.println(usuarioBuscado + " buscado.");

        if (usuarioBuscado.isPresent()) {

            Usuario usuario = usuarioBuscado.get();

            Optional<Conta> contaCorrenteBuscada = contaRepository.findByNumeroAndTipo(usuario.getLogin(), TipoConta.DEBITO.getDescricao());
            Conta ccorrente = contaCorrenteBuscada.get();
            List<Lancamento> lancamentosContaCorrente = lancamentoRepository.findTop10ByContaDestinoOrderByDataDesc(ccorrente.getId().toString());
            ContaDTO contaCorrente = new ContaDTO(ccorrente, lancamentosContaCorrente);

            Optional<Conta> contaCreditoBuscada = contaRepository.findByNumeroAndTipo(usuario.getLogin(), TipoConta.CREDITO.getDescricao());
            Conta ccredito = contaCreditoBuscada.get();
            List<Lancamento> lancamentosContaCredito = lancamentoRepository.findTop10ByContaDestinoOrderByDataDesc(ccredito.getId().toString());
            ContaDTO contaCredito = new ContaDTO(ccredito, lancamentosContaCredito);

            dashboard.setContaCorrente(contaCorrente);
            dashboard.setContaCredito(contaCredito);
        }

        return dashboard;
    }

    public ExtratoDTO getExtrato(ExtratoRequestDTO extratoRequestDto) {

        ExtratoDTO extrato = new ExtratoDTO();

        Optional<Usuario> usuarioBuscado = usuarioRepository.findByLogin(extratoRequestDto.getLogin());

        if (usuarioBuscado.isPresent()) {

            Usuario usuario = usuarioBuscado.get();

            Optional<Conta> contaCorrenteBuscada = contaRepository.findByNumeroAndTipo(usuario.getLogin(), TipoConta.DEBITO.getDescricao());
            Conta ccorrente = contaCorrenteBuscada.get();
            List<Lancamento> lancamentosContaCorrente = lancamentoRepository.findAllByDataBetweenAndContaDestino(extratoRequestDto.getInicio(), extratoRequestDto.getFim(), ccorrente.getId());
            ContaDTO contaCorrente = new ContaDTO(ccorrente, lancamentosContaCorrente);

            Optional<Conta> contaCreditoBuscada = contaRepository.findByNumeroAndTipo(usuario.getLogin(), TipoConta.CREDITO.getDescricao());
            Conta ccredito = contaCreditoBuscada.get();
            List<Lancamento> lancamentosContaCredito = lancamentoRepository.findAllByDataBetweenAndContaDestino(extratoRequestDto.getInicio(), extratoRequestDto.getFim(), ccredito.getId());
            ContaDTO contaCredito = new ContaDTO(ccredito, lancamentosContaCredito);

            extrato.setContaCorrente(contaCorrente);
            extrato.setContaCredito(contaCredito);
        }

        System.out.println(extrato);

        return extrato;
    }

}
