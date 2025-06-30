package br.knex.arthur_nicolas.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "despesa")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "data_emissao")
    private String dataEmissao;

    @Column(name = "fornecedor")
    private String fornecedor;

    @Column(name = "valor_liquido")
    private BigDecimal valorLiquido;

    @Column(name = "url_documento")
    private String urlDocumento;

    // Relação 'Muitos Para Um', relacionando com a Entity Deputado, gerando a coluna "deputado_id" para referencia-lo em JoinColumn.
    @ManyToOne
    @JoinColumn(name = "deputado_id")
    private Deputado deputado;
}
