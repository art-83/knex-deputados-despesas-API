package br.knex.arthur_nicolas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeputadoResponseDTO {
    private String id;
    private String nome;
    private String uf;
    private String cpf;
    private String partido;
}
