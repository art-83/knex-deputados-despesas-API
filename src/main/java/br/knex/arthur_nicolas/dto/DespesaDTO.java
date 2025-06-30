package br.knex.arthur_nicolas.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DespesaDTO {

    @CsvBindByName(column = "datEmissao")
    private String dataEmissao;

    @CsvBindByName(column = "txtFornecedor")
    private String fornecedor;

    @CsvBindByName(column = "vlrLiquido")
    private BigDecimal valorLiquido;

    @CsvBindByName(column = "urlDocumento")
    private String urlDocumento;

    @CsvBindByName(column = "ideCadastro")
    private String deputadoId;
}
