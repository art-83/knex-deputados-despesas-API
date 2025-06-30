package br.knex.arthur_nicolas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeputadoDTO {
    private String id;
    private String nome;
    private String uf;
    private String cpf;
    private String partido;
    private List<DespesaDTO> despesas = new ArrayList<>();
}