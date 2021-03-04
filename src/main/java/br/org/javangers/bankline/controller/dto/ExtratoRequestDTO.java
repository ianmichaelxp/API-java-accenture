package br.org.javangers.bankline.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoRequestDTO {
    private String login;
    private LocalDate inicio;
    private LocalDate fim;
}
