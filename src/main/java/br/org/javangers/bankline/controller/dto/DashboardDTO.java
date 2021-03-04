package br.org.javangers.bankline.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {
    private ContaDTO contaCorrente;
    private ContaDTO contaCredito;
}
