package br.knex.arthur_nicolas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DespesaResponseDTO {
    private String dataEmissao;
    private String fornecedor;
    private BigDecimal valorLiquido;
    private String urlDocumento;
    private String deputado;
}