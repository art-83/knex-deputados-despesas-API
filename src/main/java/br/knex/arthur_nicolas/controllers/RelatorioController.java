package br.knex.arthur_nicolas.controllers;

import br.knex.arthur_nicolas.repositories.DespesaRepository;
import br.knex.arthur_nicolas.services.DespesaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private DespesaQueryService despesaQueryService;

    @Autowired
    private DespesaRepository despesaRepository;

    @GetMapping("/total-despesas")
    public BigDecimal getTotalDespesa() {
        return despesaQueryService.getSumAllDespesa();
    }

    @GetMapping("/{id}/despesas")
    public BigDecimal getDespesaById(@PathVariable String id) {
        return despesaQueryService.getDespesaSumById(id);
    }
}
