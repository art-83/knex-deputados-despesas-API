package br.knex.arthur_nicolas.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "deputado")
@Entity
@Data
public class Deputado {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "uf")
    private String uf;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "partido")
    private String partido;

    /*
     Relação 'Um Para Muitos' configurada para fazer o gerenciamento automático da TABLE 'despesa'.

     - mappedBy: Mapear a entidade pai usando o atributo 'Deputado deputado' na classe filha (Despesa).
     - cascade: Faz o gerenciamento automático da criação de tabela 'despesa' no Banco de Dados.
     - orphanRemoval: Caso algum Deputado na tabela 'deputado' for removido, isso garante que não tenha Despesa sem dono na TABLE despesa.
    */
    @OneToMany(mappedBy = "deputado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Despesa> despesas;
}
