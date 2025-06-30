package br.knex.arthur_nicolas.controllers;

import br.knex.arthur_nicolas.dto.response.DeputadoResponseDTO;
import br.knex.arthur_nicolas.services.DeputadoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeputadoController {

    @Autowired
    private DeputadoQueryService deputadoQueryService;

    @GetMapping("/deputados")
    public List<DeputadoResponseDTO> getData(@RequestParam String uf) throws Exception {
        return deputadoQueryService.listByUf(uf);
    }
}
