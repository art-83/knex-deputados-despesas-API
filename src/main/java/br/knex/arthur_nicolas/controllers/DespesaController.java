package br.knex.arthur_nicolas.controllers;

import br.knex.arthur_nicolas.dto.response.DespesaResponseDTO;
import br.knex.arthur_nicolas.services.DespesaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private DespesaQueryService despesaQueryService;

    @GetMapping
    public List<DespesaResponseDTO> getAllDespesas() {
        return despesaQueryService.getAllDespesaData();
    }

    @GetMapping("/id/{id}")
    public List<DespesaResponseDTO> getDespesaById(@PathVariable String id) {
        return despesaQueryService.getDespesaById(id);
    }

    @GetMapping("/data-emissao/{data}")
    public List<DespesaResponseDTO> getDespesaByData(@PathVariable String data) {
        return despesaQueryService.getDespesaByData(data);
    }

    @GetMapping("/fornecedor/{fornecedor}")
    public List<DespesaResponseDTO> getDespesaByFornecedor(@PathVariable String fornecedor) {
        return despesaQueryService.getDespesaByFornecedor(fornecedor);
    }
}
