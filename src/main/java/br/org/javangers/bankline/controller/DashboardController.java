package br.org.javangers.bankline.controller;

import br.org.javangers.bankline.controller.dto.DashboardDTO;
import br.org.javangers.bankline.controller.dto.ExtratoDTO;
import br.org.javangers.bankline.controller.dto.ExtratoRequestDTO;
import br.org.javangers.bankline.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{login}")
    public DashboardDTO getDashboard(@PathVariable(value="login") String login) {
        System.out.println(login);
        return dashboardService.getUsuarioDash(login);
    }

    @PostMapping
    public ExtratoDTO getExtratoPorDatas(@RequestBody ExtratoRequestDTO extratoRequestDTO) {

        return dashboardService.getExtrato(extratoRequestDTO);
    }

}
