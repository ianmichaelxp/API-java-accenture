package br.org.javangers.bankline.controller.dto;

import br.org.javangers.bankline.model.Conta;
import br.org.javangers.bankline.model.Lancamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {
    Conta conta;
    List<Lancamento> lancamentos;
}
