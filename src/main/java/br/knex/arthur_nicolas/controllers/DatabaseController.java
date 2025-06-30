package br.knex.arthur_nicolas.controllers;

import br.knex.arthur_nicolas.importer.http.DeputadoHttpImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database/")
public class DatabaseController {
    @Autowired
    private DeputadoHttpImport deputadoHttpImport;

    @PostMapping("/init")
    public ResponseEntity<?> initDataBase() throws Exception {
        deputadoHttpImport.initDataBase();
        return ResponseEntity.ok("OK! Database is populated now.");
    }
 }